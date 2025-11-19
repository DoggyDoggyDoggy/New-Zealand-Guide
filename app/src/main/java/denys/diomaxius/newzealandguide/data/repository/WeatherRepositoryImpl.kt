package denys.diomaxius.newzealandguide.data.repository

import android.util.Log
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl (
    private val dao: CityDao,
    private val dataSource: CityWeatherDataSource
) : WeatherRepository {


    override suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather> {

        val cacheInfo = withContext(Dispatchers.IO) {
            dao.getWeatherCacheInfo(cityId)
        }

        if (cacheInfo == null) {
            Log.d("WeatherRepositoryImpl", "cacheInfo: $cacheInfo")
        }

        val weatherDtos = dataSource.fetchForecast(cityId)

        return weatherDtos.map { it.toEntity(cityId) }.map { it.toDomain() }
    }
}