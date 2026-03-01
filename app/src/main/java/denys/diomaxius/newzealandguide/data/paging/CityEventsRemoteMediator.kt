package denys.diomaxius.newzealandguide.data.paging

import android.content.Context
import coil3.imageLoader
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.google.firebase.firestore.FirebaseFirestoreException
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.remotekeys.RemoteCityEventsKeysEntity
import denys.diomaxius.newzealandguide.data.remote.api.AppConfigDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.domain.exception.MissingServerDataException
import denys.diomaxius.newzealandguide.domain.exception.NoDataAvailableException
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalPagingApi::class)
class CityEventsRemoteMediator(
    private val context: Context,
    private val cityId: String,
    private val pageSize: Int,
    private val dataSource: CityEventsDataSource,
    private val appConfigDataSource: AppConfigDataSource,
    private val remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
    private val cityDao: CityDao,
    private val logger: ErrorLogger,
) : RemoteMediator<Int, CityEventEntity>() {
    // This function decides whether to run load() at startup.
    override suspend fun initialize(): InitializeAction {
        return try {
            val localUpdateTag = remoteCityEventsKeysDao.getKeyByCityId(cityId)?.updateTag ?: "0"
            val serverUpdateTag = appConfigDataSource.getEventsUpdateTag()

            if (localUpdateTag != serverUpdateTag || localUpdateTag == "0") {
                InitializeAction.LAUNCH_INITIAL_REFRESH
            } else InitializeAction.SKIP_INITIAL_REFRESH
        } catch (_: Exception) {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CityEventEntity>,
    ): MediatorResult {
        return try {
            // Define the key (cursor) for loading
            val lastDocId = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteCityEventsKeysDao.getKeyByCityId(cityId)
                    // If APPEND, but there is no key, then we have reached the end of the list earlier
                    if (remoteKey?.lastDocId == null && remoteKey != null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey?.lastDocId
                }
            }

            // Downloading data from the network
            val response = dataSource.getEvents(
                cityId = cityId,
                limit = pageSize,
                lastDocId = lastDocId
            )

            val currentServerTag = if (loadType == LoadType.REFRESH) {
                appConfigDataSource.getEventsUpdateTag()
            } else {
                remoteCityEventsKeysDao.getKeyByCityId(cityId)?.updateTag ?: "0"
            }

            val entities = response.map { it.toEntity(cityId) }
            val endOfPaginationReached = entities.size < pageSize

            var shouldLogEmptyServer = false

            // Save to DB (Transaction)
            val result = database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    if (entities.isNotEmpty()) {
                        clearCache()
                        saveEvents(entities, loadType)
                        saveRemoteKey(entities.lastOrNull(), endOfPaginationReached, currentServerTag)
                        MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                    } else {
                        shouldLogEmptyServer = true

                        if (cityDao.getEventsCount(cityId) == 0) {
                            MediatorResult.Error(NoDataAvailableException())
                        } else {
                            MediatorResult.Success(endOfPaginationReached = true)
                        }
                    }
                } else {
                    if (entities.isNotEmpty()) {
                        saveEvents(entities, loadType)
                        saveRemoteKey(entities.lastOrNull(), endOfPaginationReached, currentServerTag)
                    } else if (endOfPaginationReached) {
                        saveRemoteKey(null, true, currentServerTag)
                    }
                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }

            if (shouldLogEmptyServer) {
                val errorMsg = "Server returned empty events for city $cityId"
                logger.logMessage(errorMsg)
                logger.logException(
                    MissingServerDataException(errorMsg),
                    mapOf("cityId" to cityId)
                )
            }

            if (entities.isNotEmpty()) {
                prefetchImages(entities)
            }

            result

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            val isIgnoreLog = when (e) {
                is FirebaseFirestoreException -> e.code == FirebaseFirestoreException.Code.UNAVAILABLE
                is IOException -> false // Probably change later to "true"
                else -> false
            }

            if (isIgnoreLog) {
                logger.logException(e, mapOf("cityId" to cityId, "loadType" to loadType.toString()))
            }

            if (cityDao.getEventsCount(cityId) == 0)
                MediatorResult.Error(NoDataAvailableException())
            else
                MediatorResult.Error(e)
        }
    }

    // --- Helper methods for code cleanliness ---

    private suspend fun clearCache() {
        remoteCityEventsKeysDao.clearKeyByCityId(cityId)
        cityDao.deleteEventsByCityId(cityId)
    }

    private suspend fun saveEvents(newEvents: List<CityEventEntity>, loadType: LoadType) {
        if (newEvents.isEmpty()) return

        // Calculate the starting position for sorting
        val startPosition = if (loadType == LoadType.REFRESH) {
            0
        } else {
            // Take the maximum position + 1. If there is no base, then 0.
            (cityDao.getMaxPosition(cityId) ?: -1) + 1
        }

        // Assigning positions to elements
        val eventsWithPosition = newEvents.mapIndexed { index, event ->
            event.copy(positionInList = startPosition + index)
        }

        // Use InsertOrReplace in the DAO to avoid manual duplicate checking.
        cityDao.insertOrReplaceCityEvents(eventsWithPosition)
    }

    private suspend fun saveRemoteKey(
        lastEvent: CityEventEntity?,
        endOfPagination: Boolean,
        currentServerTag: String
    ) {
        val existingKey = remoteCityEventsKeysDao.getKeyByCityId(cityId)

        // If the data runs out, null; otherwise, the ID of the last event.
        val nextKey = if (endOfPagination) null else (lastEvent?.eventId ?: existingKey?.lastDocId)

        val keyEntity = RemoteCityEventsKeysEntity(
            cityId = cityId,
            lastDocId = nextKey,
            updateTag = currentServerTag
        )
        remoteCityEventsKeysDao.insertKey(keyEntity)
    }

    private fun prefetchImages(entities: List<CityEventEntity>) {
        val imageLoader = context.imageLoader

        entities.forEach { event ->
            val request = ImageRequest.Builder(context)
                .data(event.imageUrl)
                //Write only to disk, without taking up random access memory (RAM)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()

            // Start asynchronous loading
            imageLoader.enqueue(request)
        }
    }
}