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
                val nextPos = if (loadType == LoadType.REFRESH) {
                    0
                } else {
                    (cityDao.getMaxPosition(cityId) ?: 0) + 1
                }

                val entitiesWithIndex = entities.mapIndexed { index, entity ->
                    entity.copy(positionInList = nextPos + index)
                }

                if (loadType == LoadType.REFRESH) {
                    // Очистить старые события и ключи для этого города
                    cityDao.deleteEventsByCityId(cityId)
                    remoteCityEventsKeysDao.clearKeyByCityId(cityId)
                }

                cityDao.insertOrReplaceCityEvents(entitiesWithIndex)

                // Сохраняем новый ключ — last document id из последнего документа списка
                if (entities.isNotEmpty()) {
                    val newLastDocId = entities.last()

                    Log.d("CityEventsRemoteMediator", "lastDocId: ${newLastDocId.eventId}")

                    val newKey = RemoteCityEventsKeysEntity(
                        cityId = cityId,
                        lastDocId = newLastDocId.eventId
                    )

                    remoteCityEventsKeysDao.insertKey(newKey)
                } else {
                    // Если пустая страница — ставим ключ в null (означает конец)
                    // Можно оставить как есть — в APPEND мы будем считать конец, если lastDocId == null
                    remoteCityEventsKeysDao.clearKeyByCityId(cityId)
                }
            }

            return MediatorResult.Success(endOfPaginationReached = entities.isEmpty() || (entities.size < pageSize))
        } catch (e: Exception) {
            Log.e("CityEventsRemoteMediator", "Error fetching events", e)
            return MediatorResult.Error(e)
        }
    }
}