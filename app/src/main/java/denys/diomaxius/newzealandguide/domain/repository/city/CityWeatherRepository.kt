package denys.diomaxius.newzealandguide.domain.repository.city

import denys.diomaxius.newzealandguide.domain.model.city.CityWeather

interface CityWeatherRepository {
    suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather>
}