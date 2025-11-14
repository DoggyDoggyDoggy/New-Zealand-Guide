package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.data.local.room.dao.CityHistoryDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.repository.CityHistoryRepository

class CityHistoryRepositoryImpl(
    private val cityHistoryDao: CityHistoryDao
) : CityHistoryRepository {
    override suspend fun getCityHistoryByCityId(cityId: String): CityHistory =
        cityHistoryDao.getCityHistoryByCityId(cityId).toDomain()
}