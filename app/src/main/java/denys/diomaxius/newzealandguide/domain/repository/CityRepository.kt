package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic

interface CityRepository {
    suspend fun getAllCities(): List<City>
    suspend fun getPlacesByCityId(cityId: Int): List<CityPlaceTopic>
    fun getCityById(id: Int): City
}