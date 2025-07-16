package denys.diomaxius.newzealandguide.domain.model.repository

import denys.diomaxius.newzealandguide.domain.model.city.City

interface CityRepository {
    fun getAllCities(): List<City>
    fun getCityById(id: Int) : City
}