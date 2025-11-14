package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity

@Dao
interface CityPlacesDao {
    @Query("SELECT * FROM city_place WHERE cityId = :cityId")
    suspend fun getPlacesForCity(cityId: String): List<CityPlaceEntity>
}