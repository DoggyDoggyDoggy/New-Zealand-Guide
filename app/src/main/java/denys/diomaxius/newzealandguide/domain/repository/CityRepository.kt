package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic

interface CityRepository {
    suspend fun getAllCities(): List<City>
    suspend fun getPlacesByCityId(cityId: String): List<CityPlaceTopic>
    suspend fun getCityById(id: String): City
}