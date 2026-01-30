package denys.diomaxius.newzealandguide.data.paging

import android.content.Context
import coil3.imageLoader
import coil3.request.CachePolicy
import coil3.request.ImageRequest
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
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class CityEventsRemoteMediator(
    private val context: Context,
    private val cityId: String,
    private val pageSize: Int,
    private val dataSource: CityEventsDataSource,
    private val remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
    private val cityDao: CityDao,
) : RemoteMediator<Int, CityEventEntity>() {

    // Настройка времени жизни кэша (7 дней)
    private val cacheTimeout = TimeUnit.DAYS.toMillis(7)

    /**
     * Эта функция решает, нужно ли запускать load() при старте.
     */
    override suspend fun initialize(): InitializeAction {
        val remoteKey = remoteCityEventsKeysDao.getKeyByCityId(cityId)
        val lastUpdated = remoteKey?.lastUpdated ?: 0L
        val now = System.currentTimeMillis()

        // Если ключа нет (первый запуск) ИЛИ время истекло -> LAUNCH (Запускаем REFRESH)
        // Иначе -> SKIP (Показываем локальные данные)
        return if (remoteKey == null || (now - lastUpdated > cacheTimeout)) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CityEventEntity>,
    ): MediatorResult {
        return try {
            // 1. Определяем ключ (cursor) для загрузки
            val lastDocId = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = remoteCityEventsKeysDao.getKeyByCityId(cityId)
                    // Если APPEND, но ключа нет — значит, мы достигли конца списка ранее
                    if (remoteKey?.lastDocId == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.lastDocId
                }
            }

            // 2. Загружаем данные из сети
            val response = dataSource.getEvents(
                cityId = cityId,
                limit = pageSize,
                lastDocId = lastDocId
            )

            // Преобразуем DTO в Entity сразу
            val entities = response.map { it.toEntity(cityId) }
            val endOfPaginationReached = entities.size < pageSize

            // 3. Сохраняем в БД (Транзакция)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    clearCache()
                }

                saveEvents(entities, loadType)
                saveRemoteKey(entities.lastOrNull(), endOfPaginationReached)
            }

            prefetchImages(entities)

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            Log.e("CityEventsRemoteMediator", "Error loading data", e)
            MediatorResult.Error(e)
        }
    }

    // --- Вспомогательные методы для чистоты кода ---

    private suspend fun clearCache() {
        remoteCityEventsKeysDao.clearKeyByCityId(cityId)
        cityDao.deleteEventsByCityId(cityId)
    }

    private suspend fun saveEvents(newEvents: List<CityEventEntity>, loadType: LoadType) {
        if (newEvents.isEmpty()) return

        // Вычисляем начальную позицию для сортировки
        val startPosition = if (loadType == LoadType.REFRESH) {
            0
        } else {
            // Берем максимальную позицию + 1. Если базы нет, то 0.
            (cityDao.getMaxPosition(cityId) ?: -1) + 1
        }

        // Присваиваем позиции элементам
        val eventsWithPosition = newEvents.mapIndexed { index, event ->
            event.copy(positionInList = startPosition + index)
        }

        // Используем InsertOrReplace в DAO, чтобы не делать ручную проверку на дубликаты
        // Это быстрее и чище. Room сам обновит данные, если ID совпадут.
        cityDao.insertOrReplaceCityEvents(eventsWithPosition)
    }

    private suspend fun saveRemoteKey(lastEvent: CityEventEntity?, endOfPagination: Boolean) {
        // Если данные кончились, сохраняем null как маркер конца
        // Если данные есть, берем ID последнего элемента
        val nextKey = if (endOfPagination && lastEvent == null) null else lastEvent?.eventId

        val keyEntity = RemoteCityEventsKeysEntity(
            cityId = cityId,
            lastDocId = nextKey,
            lastUpdated = System.currentTimeMillis() // Обновляем время при каждой успешной загрузке
        )
        remoteCityEventsKeysDao.insertKey(keyEntity)
    }

    private fun prefetchImages(entities: List<CityEventEntity>) {
        val imageLoader = context.imageLoader

        entities.forEach { event ->
            val request = ImageRequest.Builder(context)
                .data(event.imageUrl)
                // Записываем только на диск, не занимая оперативную память (RAM)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()

            // Запускаем асинхронную загрузку
            imageLoader.enqueue(request)
        }
    }
}