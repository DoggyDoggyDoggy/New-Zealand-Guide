package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("""
        SELECT * FROM cities 
        WHERE (:showOnlyFavorites = 0 OR favorite = 1)
        ORDER BY name COLLATE NOCASE ASC
    """)
    fun getAllCitiesFlow(showOnlyFavorites: Boolean): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE favorite = 1 ORDER BY name COLLATE NOCASE ASC")
    fun getAllFavoriteCitiesFlow(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCityById(cityId: String): CityEntity

    @Query("SELECT * FROM city_history WHERE cityId = :cityId")
    suspend fun getCityHistoryByCityId(cityId: String): CityHistoryEntity

    @Query("SELECT * FROM city_place WHERE cityId = :cityId")
    suspend fun getPlacesForCity(cityId: String): List<CityPlaceEntity>

    @Query("UPDATE cities SET favorite = NOT favorite WHERE id = :cityId")
    suspend fun toggleFavorite(cityId: String)

    //Weather
    @Query("SELECT * FROM weather_cache_info WHERE cityId = :cityId")
    fun getWeatherCacheInfo(cityId: String): WeatherCacheInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateWeatherCacheInfo(info: WeatherCacheInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeatherForecast(weatherList: List<CityWeatherEntity>)

    @Query("SELECT * FROM city_weather WHERE cityId = :cityId ORDER BY dateTime ASC")
    fun getCityWeatherForecast(cityId: String): List<CityWeatherEntity>

    @Query("DELETE FROM city_weather WHERE cityId = :cityId")
    suspend fun deleteCityWeatherForecast(cityId: String)

    @Transaction
    suspend fun replaceWeatherForecast(
        cityId: String,
        newForecast: List<CityWeatherEntity>,
        cacheInfo: WeatherCacheInfo
    ) {
        deleteCityWeatherForecast(cityId)

        insertAllWeatherForecast(newForecast)

        insertOrUpdateWeatherCacheInfo(cacheInfo)
    }

    //Events
    @Query("SELECT * FROM city_events WHERE cityId = :cityId AND eventId = :eventId")
    suspend fun getCityEvent(cityId: String, eventId: String): CityEventEntity

    @Query("SELECT * FROM city_events WHERE cityId = :cityId ORDER BY positionInList ASC")
    fun getCityEventsPagingSource(cityId: String): PagingSource<Int, CityEventEntity>

    @Query("DELETE FROM city_events WHERE cityId = :cityId")
    suspend fun deleteEventsByCityId(cityId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceCityEvents(events: List<CityEventEntity>)

    @Query("SELECT MAX(positionInList) FROM city_events WHERE cityId = :cityId")
    suspend fun getMaxPosition(cityId: String): Int?

    @Query("SELECT eventId FROM city_events WHERE cityId = :cityId")
    suspend fun getStoredEventIds(cityId: String): List<String>
}