package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.CityWeather

interface WeatherRepository {
    suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather>
}