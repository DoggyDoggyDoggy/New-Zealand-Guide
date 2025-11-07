package denys.diomaxius.newzealandguide.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import denys.diomaxius.newzealandguide.data.local.room.converter.Converters
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Database(
    entities = [
        CityEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CityDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "city_db"
    }

    abstract fun cityDao(): CityDao
}