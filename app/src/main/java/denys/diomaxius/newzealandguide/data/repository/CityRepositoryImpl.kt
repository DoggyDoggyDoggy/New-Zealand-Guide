package denys.diomaxius.newzealandguide.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.paging.CityEventsRemoteMediator
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.collections.map
import kotlinx.coroutines.flow.map as flowMap

private const val MAX_CACHE_AGE_HOURS = 36L

class CityRepositoryImpl(
    private val cityDao: CityDao,
    private val weatherDataSource: CityWeatherDataSource,
    private val eventsDataSource: CityEventsDataSource,
    private val remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
) : CityRepository {

    private suspend fun shouldFetchNewWeather(cityId: String): Boolean {
        val cacheInfo = withContext(Dispatchers.IO) {
            cityDao.getWeatherCacheInfo(cityId)
        }

        val lastSynced: Instant = cacheInfo?.lastSyncedTimestamp ?: return true

        val hoursPassed = ChronoUnit.HOURS.between(lastSynced, Instant.now())

        return hoursPassed >= MAX_CACHE_AGE_HOURS
    }

    override fun getAllCitiesFlow(): Flow<List<City>> =
        cityDao.getAllCitiesFlow().map { list -> list.map(CityEntity::toDomain) }


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

    override suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather> {
        val shouldFetch = shouldFetchNewWeather(cityId)

        if (shouldFetch) {

            Log.i("WeatherRepositoryImpl", "Fetching new weather")

            val weatherDto = weatherDataSource.fetchForecast(cityId)
            val newForecastEntities = weatherDto.map { dto -> dto.toEntity(cityId) }

            val newCacheInfo = WeatherCacheInfo(cityId, Instant.now())

            withContext(Dispatchers.IO) {
                cityDao.replaceWeatherForecast(cityId, newForecastEntities, newCacheInfo)
            }
        }

        return withContext(Dispatchers.IO) {
            cityDao.getCityWeatherForecast(cityId)
                .map(CityWeatherEntity::toDomain)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun cityEventsPagerFlow(
        pageSize: Int,
        cityId: String,
    ): Flow<PagingData<CityEvent>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                initialLoadSize = pageSize,
                prefetchDistance = pageSize / 2
            ),
            remoteMediator = CityEventsRemoteMediator(
                cityId = cityId,
                pageSize = pageSize,
                dataSource = eventsDataSource,
                remoteCityEventsKeysDao = remoteCityEventsKeysDao,
                cityDao = cityDao,
                database = database
            ),
            pagingSourceFactory = { cityDao.getCityEventsPagingSource(cityId) }
        ).flow
            .flowMap { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }
    }
}