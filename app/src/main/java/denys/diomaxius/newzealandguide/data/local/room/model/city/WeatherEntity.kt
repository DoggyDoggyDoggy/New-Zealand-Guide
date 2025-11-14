package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city_weather",
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
data class WeatherEntity(
    @PrimaryKey
    val cityId: String,
    val temp: Double = 0.0,
    val description: String = "",
    val icon: String = "",
    val date: String = "",
)
