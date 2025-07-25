package denys.diomaxius.newzealandguide.data.mapper.event

import denys.diomaxius.newzealandguide.data.model.event.EventDto
import denys.diomaxius.newzealandguide.domain.model.event.Event

fun EventDto.toDomain(): Event =
    Event(
        id = id,
        url = url,
        name = name,
        description = description,
        address = address,
        sessions = sessions.map { it.toDomain() },
        imageUrl = imageUrl
    )