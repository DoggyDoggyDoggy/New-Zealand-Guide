package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

fun CityEventEntity.toDomain() : CityEvent =
    CityEvent(
        cityId = cityId,
        eventId = eventId,
        url = url,
        name = name,
        description = description,
        address = address,
        imageUrl = imageUrl,
        sessions = sessions
    )