package denys.diomaxius.newzealandguide.data.mapper.event

import denys.diomaxius.newzealandguide.data.model.event.SessionDto
import denys.diomaxius.newzealandguide.domain.model.event.Session

fun SessionDto.toDomain(): Session =
    Session(dateTimeSummary = dateTimeSummary)