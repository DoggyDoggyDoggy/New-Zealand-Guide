package denys.diomaxius.newzealandguide.data.local.room.model.event

import androidx.room.Entity
import androidx.room.ForeignKey
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Entity(
    tableName = "city_events",
    primaryKeys = ["cityId", "eventId"],
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EventEntity(
    val cityId: String,
    val eventId: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val imageUrl: String,
)