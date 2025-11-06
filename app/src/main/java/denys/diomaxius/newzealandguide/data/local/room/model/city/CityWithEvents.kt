package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Embedded
import androidx.room.Relation
import denys.diomaxius.newzealandguide.data.local.room.model.event.EventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.event.EventWithSessions

data class CityWithEvents(
    @Embedded val city: CityEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId",
        entity = EventEntity::class
    )
    val events: List<EventWithSessions>
)