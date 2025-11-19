package denys.diomaxius.newzealandguide.data.remote.api

import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto

interface CityWeatherDataSource {
    suspend fun fetchForecast(cityId: String): List<CityWeatherDto>
}