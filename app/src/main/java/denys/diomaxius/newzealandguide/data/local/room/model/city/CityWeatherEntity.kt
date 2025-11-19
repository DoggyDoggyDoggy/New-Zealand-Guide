package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city_weather",
    primaryKeys = ["cityId", "dateTime"],
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
data class CityWeatherEntity(
    val cityId: String,
    val dateTime: String,
    val temp: Double,
    val description: String,
    val icon: String
)