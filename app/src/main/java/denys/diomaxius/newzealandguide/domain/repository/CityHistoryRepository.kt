package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.cityhistory.CityHistory

interface CityHistoryRepository {
    suspend fun getCityHistoryByCityId(cityId: String): CityHistory
}