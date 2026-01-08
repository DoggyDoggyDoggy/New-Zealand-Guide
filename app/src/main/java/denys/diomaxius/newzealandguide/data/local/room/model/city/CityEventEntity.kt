package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "city_events",
    primaryKeys = ["cityId", "eventId"],
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class CityEventEntity(
    val cityId: String,
    val eventId: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val imageUrl: String,
    val sessions: List<String>,
    val positionInList: Int = 0
)