package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cities ORDER BY name COLLATE NOCASE ASC")
    fun citiesPagingSource(): PagingSource<Int, CityEntity>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCityById(cityId: String): CityEntity
}