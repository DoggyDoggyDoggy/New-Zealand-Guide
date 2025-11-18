package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cities ORDER BY name COLLATE NOCASE ASC")
    fun citiesPagingSource(): PagingSource<Int, CityEntity>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCityById(cityId: String): CityEntity

    @Query("SELECT * FROM city_history WHERE cityId = :cityId")
    suspend fun getCityHistoryByCityId(cityId: String): CityHistoryEntity

    @Query("SELECT * FROM city_place WHERE cityId = :cityId")
    suspend fun getPlacesForCity(cityId: String): List<CityPlaceEntity>

    @Query("SELECT * FROM weather_cache_info WHERE cityId = :cityId")
    fun getWeatherCacheInfo(cityId: String): WeatherCacheInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateWeatherCacheInfo(info: WeatherCacheInfo)
}