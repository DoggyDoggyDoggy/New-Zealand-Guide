package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.data.local.room.dao.CityPlacesDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.domain.model.cityplace.CityPlace
import denys.diomaxius.newzealandguide.domain.repository.CityPlacesRepository

class CityPlacesRepositoryImpl(
    private val cityPlacesDao: CityPlacesDao
) : CityPlacesRepository {
    override suspend fun getPlacesForCityById(cityId: String): List<CityPlace> =
        cityPlacesDao.getPlacesForCity(cityId).map { it.toDomain() }
}