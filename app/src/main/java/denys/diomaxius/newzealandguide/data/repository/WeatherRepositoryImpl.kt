package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeatherByCityId(cityId: String): List<Weather> {
        TODO("Not yet implemented")
    }
}