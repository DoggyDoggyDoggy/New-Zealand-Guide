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
}