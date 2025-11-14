package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.city.CityPlace

interface CityPlacesRepository {
    suspend fun getPlacesForCityById(cityId: String) : List<CityPlace>
}