package denys.diomaxius.newzealandguide.data.local.room.model.event

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Entity(
    tableName = "events",
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
    @PrimaryKey
    val id: String,
    val cityId: String,
    val firebaseEventId: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val imageUrl: String,
)