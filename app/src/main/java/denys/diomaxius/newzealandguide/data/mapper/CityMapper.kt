package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityEntity
import denys.diomaxius.newzealandguide.domain.model.city.City

fun CityEntity.toDomain(): City = City(
    id = id,
    name = name,
    photos = photos
)