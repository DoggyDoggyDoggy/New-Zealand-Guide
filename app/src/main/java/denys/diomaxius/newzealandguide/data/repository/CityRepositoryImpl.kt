package denys.diomaxius.newzealandguide.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.google.firebase.firestore.FirebaseFirestoreException
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.paging.CityEventsRemoteMediator
import denys.diomaxius.newzealandguide.data.remote.api.AppConfigDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.domain.exception.MissingServerDataException
import denys.diomaxius.newzealandguide.domain.exception.NoDataAvailableException
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.model.city.WeatherResult
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.collections.map
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.map as flowMap

class CityRepositoryImpl(
    private val context: Context,
    private val cityDao: CityDao,
    private val weatherDataSource: CityWeatherDataSource,
    private val eventsDataSource: CityEventsDataSource,
    private val appConfigDataSource: AppConfigDataSource,
    private val remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
    private val logger: ErrorLogger,
) : CityRepository {

    private suspend fun shouldFetchNewWeather(cityId: String, serverTag: String): Boolean {
        if (serverTag.isEmpty() || serverTag == "0") return false

        val cacheInfo = withContext(Dispatchers.IO) {
            cityDao.getWeatherCacheInfo(cityId)
        }

        val localTag = cacheInfo?.updateTag ?: "0"

        return serverTag != localTag
    }

    override fun getAllCitiesFlow(onlyFavorites: Boolean): Flow<List<City>> =
        cityDao.getAllCitiesFlow(onlyFavorites).map { list -> list.map(CityEntity::toDomain) }

    override suspend fun getCityById(cityId: String): City =
        cityDao.getCityById(cityId).toDomain()

    override suspend fun getPlacesForCityById(cityId: String): List<CityPlace> =
        cityDao.getPlacesForCity(cityId).map { it.toDomain() }

    override suspend fun getCityHistoryByCityId(cityId: String): CityHistory =
        cityDao.getCityHistoryByCityId(cityId).toDomain()

    override suspend fun getCityEvent(cityId: String, eventId: String): CityEvent =
        cityDao.getCityEvent(cityId, eventId).toDomain()

    override suspend fun toggleFavorite(cityId: String) {
        cityDao.toggleFavorite(cityId)
    }

    override suspend fun getCityWeatherByCityId(cityId: String): WeatherResult =
        withContext(Dispatchers.IO) {
            try {
                val serverTag = appConfigDataSource.getWeatherUpdateTag()

                if (shouldFetchNewWeather(cityId, serverTag)) {
                    val weatherDto = weatherDataSource.fetchForecast(cityId)

                    if (weatherDto.isEmpty()) {
                        val errorMsg = "Server returned empty forecast for city $cityId"
                        logger.logMessage(errorMsg)
                        logger.logException(
                            MissingServerDataException(errorMsg),
                            mapOf("cityId" to cityId)
                        )
                    } else {
                        cityDao.replaceWeatherForecast(
                            cityId,
                            weatherDto.map { it.toEntity(cityId) },
                            WeatherCacheInfo(cityId, serverTag)
                        )
                    }
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                if (e is FirebaseFirestoreException && e.code != FirebaseFirestoreException.Code.UNAVAILABLE) {
                    logger.logException(e, mapOf("cityId" to cityId))
                } else if (e !is FirebaseFirestoreException) {
                    logger.logException(e, mapOf("cityId" to cityId, "phase" to "network_sync"))
                }
            }

            return@withContext try {
                val entities = cityDao.getCityWeatherForecast(cityId)

                if (entities.isEmpty()) {
                    WeatherResult.Error(NoDataAvailableException())
                } else {
                    val domainData = entities.map { it.toDomain() }
                    WeatherResult.Success(domainData)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e

                logger.logException(e, mapOf("cityId" to cityId, "phase" to "local_db_read"))
                WeatherResult.Error(e)
            }
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun cityEventsPagerFlow(
        pageSize: Int,
        cityId: String
    ): Flow<PagingData<CityEvent>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                initialLoadSize = pageSize,
                prefetchDistance = pageSize / 2
            ),
            remoteMediator = CityEventsRemoteMediator(
                context = context,
                cityId = cityId,
                pageSize = pageSize,
                dataSource = eventsDataSource,
                appConfigDataSource = appConfigDataSource,
                remoteCityEventsKeysDao = remoteCityEventsKeysDao,
                cityDao = cityDao,
                database = database,
                logger = logger
            ),
            pagingSourceFactory = { cityDao.getCityEventsPagingSource(cityId) }
        ).flow
            .flowMap { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }
    }
}