package denys.diomaxius.newzealandguide.data.local.room.model.event

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sessions",
    foreignKeys = [
        ForeignKey(
            entity = EventEntity::class,
            parentColumns = ["id"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SessionEntity(
    @PrimaryKey
    val sessionId: String,
    val eventId: String,
    val dateTimeSummary: String
)