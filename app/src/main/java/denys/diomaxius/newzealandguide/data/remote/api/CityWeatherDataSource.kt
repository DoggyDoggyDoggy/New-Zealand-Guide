package denys.diomaxius.newzealandguide.data.remote.api

import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import java.time.Instant

interface CityWeatherDataSource {
    suspend fun fetchForecast(cityId: String): List<CityWeatherDto>
    suspend fun fetchLastUpdatedAt(cityId: String): Instant?
}