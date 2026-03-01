package denys.diomaxius.newzealandguide.data.local.room.model.cache

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Entity(
    tableName = "weather_cache_info",
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index("cityId")]
)

data class WeatherCacheInfo(
    @PrimaryKey val cityId: String,
    val updateTag: String
)