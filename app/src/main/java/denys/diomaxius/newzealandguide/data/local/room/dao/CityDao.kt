package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWithEvents

@Dao
interface CityDao {
    @Query("SELECT * FROM cities ORDER BY name COLLATE NOCASE ASC")
    fun citiesPagingSource(): PagingSource<Int, CityEntity>

    //May be redundant
    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityEntity>

    //Refactor to CityDetails or delete
    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCityWithEventsById(cityId: String): CityWithEvents

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)

    @Query("DELETE FROM cities")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM cities")
    suspend fun countCities(): Int
}