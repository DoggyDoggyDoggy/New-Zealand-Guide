package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.CityWeather

interface WeatherRepository {
    suspend fun getWeatherByCityId(cityId: String): List<CityWeather>
}