package denys.diomaxius.newzealandguide.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import denys.diomaxius.newzealandguide.data.local.room.converter.Converters
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.model.cache.WeatherCacheInfo
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.local.room.model.remotekeys.RemoteCityEventsKeysEntity

@Database(
    entities = [
        CityEntity::class,
        CityHistoryEntity::class,
        CityPlaceEntity::class,
        WeatherCacheInfo::class,
        CityWeatherEntity::class,
        CityEventEntity::class,
        RemoteCityEventsKeysEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CityDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "city_db"
    }

    abstract fun cityDao(): CityDao
    abstract fun remoteCityEventsKeysDao(): RemoteCityEventsKeysDao
}