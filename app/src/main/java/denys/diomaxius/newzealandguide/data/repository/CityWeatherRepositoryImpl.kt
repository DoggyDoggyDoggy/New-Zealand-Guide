package denys.diomaxius.newzealandguide.data.repository

import android.util.Log
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.city.CityWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.temporal.ChronoUnit

private const val MAX_CACHE_AGE_HOURS = 36L

class CityWeatherRepositoryImpl (
    private val dao: CityDao,
    private val dataSource: CityWeatherDataSource
) : CityWeatherRepository {
    private suspend fun shouldFetchNewWeather(cityId: String): Boolean {
        val cacheInfo = withContext(Dispatchers.IO) {
            dao.getWeatherCacheInfo(cityId)
        }

        val lastSynced: Instant = cacheInfo?.lastSyncedTimestamp ?: return true

        val hoursPassed = ChronoUnit.HOURS.between(lastSynced, Instant.now())

        return hoursPassed >= MAX_CACHE_AGE_HOURS
    }

    override suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather> {
        val shouldFetch = shouldFetchNewWeather(cityId)

        if (shouldFetch) {

            Log.i("WeatherRepositoryImpl", "Fetching new weather")

            val weatherDto = dataSource.fetchForecast(cityId)
            val newForecastEntities = weatherDto.map { dto -> dto.toEntity(cityId) }

            val newCacheInfo = WeatherCacheInfo(cityId, Instant.now())

            withContext(Dispatchers.IO) {
                dao.replaceWeatherForecast(cityId, newForecastEntities, newCacheInfo)
            }
        }

        return withContext(Dispatchers.IO) {
            dao.getCityWeatherForecast(cityId)
                .map(CityWeatherEntity::toDomain)
        }
    }
}