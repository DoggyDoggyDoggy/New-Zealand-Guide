package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import denys.diomaxius.newzealandguide.domain.model.weather.WeatherIcon

interface WeatherRepository {
    suspend fun getWeatherByCityId(cityId: String): List<Weather>
    suspend fun getWeatherIcons(): List<WeatherIcon>
}