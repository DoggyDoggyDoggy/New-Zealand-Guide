package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.data.local.room.model.cityhistory.CityHistory

interface CityHistoryRepository {
    suspend fun getCityHistoryByCityId(cityId: String): CityHistory
}