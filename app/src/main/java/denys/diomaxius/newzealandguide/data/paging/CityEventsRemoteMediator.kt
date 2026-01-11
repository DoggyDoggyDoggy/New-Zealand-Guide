package denys.diomaxius.newzealandguide.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.remotekeys.RemoteCityEventsKeysEntity
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity

@OptIn(ExperimentalPagingApi::class)
class CityEventsRemoteMediator(
    private val cityId: String,
    private val pageSize: Int,
    private val dataSource: CityEventsDataSource,
    private val remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
    private val cityDao: CityDao,
) : RemoteMediator<Int, CityEventEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CityEventEntity>,
    ): MediatorResult {
        try {
            Log.d("CityEventsRemoteMediator", "loadType: $loadType")

            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // Определяем cursor (lastDocId) для запроса
            val lastDocId: String? = when (loadType) {
                LoadType.REFRESH -> {
                    // при REFRESH начинаем сначала (null -> старт запроса)
                    null
                }

                LoadType.APPEND -> {
                    // при APPEND — берем сохранённый cursor для этого города
                    val keyEntity = remoteCityEventsKeysDao.getKeyByCityId(cityId)
                    // если ключа нет — значит либо ещё не было загрузки, либо конец
                    keyEntity?.lastDocId
                }

                else -> null
            }

            // Если APPEND и нет ключа — считаем, что пагинация окончена
            if (loadType == LoadType.APPEND && lastDocId == null) {
                Log.d("CityEventsRemoteMediator", "End of pagination")
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            Log.d("CityEventsRemoteMediator", "Get entities. CityId: $cityId")

            val entities = dataSource.getEvents(
                cityId = cityId,
                limit = pageSize,
                lastDocId = lastDocId
            ).map { it.toEntity(cityId) }

            Log.d("CityEventsRemoteMediator", "database add")

            database.withTransaction {
                // 1. Если REFRESH - чистим всё
                if (loadType == LoadType.REFRESH) {
                    cityDao.deleteEventsByCityId(cityId)
                    remoteCityEventsKeysDao.clearKeyByCityId(cityId)
                }

                // 2. Получаем ID тех, кто уже в базе, чтобы не было дублей
                val storedIds = if (loadType == LoadType.APPEND) {
                    cityDao.getStoredEventIds(cityId).toHashSet()
                } else {
                    emptySet()
                }

                // 3. Оставляем только РЕАЛЬНО новые ивенты
                val newEntities = entities.filter { it.eventId !in storedIds }

                if (newEntities.isNotEmpty()) {
                    val lastPos = cityDao.getMaxPosition(cityId) ?: -1
                    val nextPos = lastPos + 1

                    val entitiesWithIndex = newEntities.mapIndexed { index, entity ->
                        entity.copy(positionInList = nextPos + index)
                    }

                    cityDao.insertOrReplaceCityEvents(entitiesWithIndex)
                }

                // 4. Ключи сохраняем на основе ОРИГИНАЛЬНОГО последнего элемента из сети
                if (entities.isNotEmpty()) {
                    val lastEntity = entities.last()
                    remoteCityEventsKeysDao.insertKey(
                        RemoteCityEventsKeysEntity(cityId, lastEntity.eventId)
                    )
                }
            }

            return MediatorResult.Success(endOfPaginationReached = entities.isEmpty() || (entities.size < pageSize))
        } catch (e: Exception) {
            Log.e("CityEventsRemoteMediator", "Error fetching events", e)
            return MediatorResult.Error(e)
        }
    }
}