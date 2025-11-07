package denys.diomaxius.newzealandguide.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Database(
    entities = [
        CityEntity::class
    ],
    version = 1
)
abstract class CityDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "city_db"
    }

    abstract fun cityDao(): CityDao
}