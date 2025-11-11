package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.room.Query
import denys.diomaxius.newzealandguide.domain.model.cityhistory.CityHistoryEntity

interface CityHistoryDao {
    @Query("SELECT * FROM city_history WHERE cityId = :cityId")
    suspend fun getCityHistoryByCityId(cityId: String): CityHistoryEntity
}