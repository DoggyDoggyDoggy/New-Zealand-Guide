package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.data.local.room.dao.CityHistoryDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.local.room.model.cityhistory.CityHistory
import denys.diomaxius.newzealandguide.domain.repository.CityHistoryRepository

class CityHistoryImpl(
    private val cityHistoryDao: CityHistoryDao
) : CityHistoryRepository {
    override suspend fun getCityHistoryByCityId(cityId: String): CityHistory =
        cityHistoryDao.getCityHistoryByCityId(cityId).toDomain()
}