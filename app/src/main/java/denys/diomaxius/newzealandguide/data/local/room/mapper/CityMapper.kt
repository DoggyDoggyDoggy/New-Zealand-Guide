package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.domain.model.City

fun CityEntity.toDomain(): City =
    City(
        id = id,
        name = name,
        photos = photos
    )