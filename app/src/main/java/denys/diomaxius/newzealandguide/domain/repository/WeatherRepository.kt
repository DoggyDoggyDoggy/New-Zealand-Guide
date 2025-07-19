package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.weather.Weather

interface WeatherRepository {
    suspend fun getWeatherByCityId(cityId: String): List<Weather>
}