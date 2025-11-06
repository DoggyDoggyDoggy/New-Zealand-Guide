package denys.diomaxius.newzealandguide.data.local.room.model.event

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithSessions(
    @Embedded
    val event: EventEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventId"
    )
    val sessions: List<SessionEntity>
)