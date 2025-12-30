package denys.diomaxius.newzealandguide.data.remote.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto

fun CityEventDto.toEntity(cityId: String): CityEventEntity =
    CityEventEntity(
        cityId = cityId,
        eventId = eventId,
        url = url,
        name = name,
        description = description,
        address = address,
        imageUrl = imageUrl,
        sessions = sessions
    )