package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.City

interface CityRepository {
    suspend fun getAllCities(): List<City>
    fun getCityById(id: Int) : City
}