package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityEntity
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory

fun CityEntity.toDomain(): City = City(
    id = id.toIntOrNull() ?: 0,
    name = name,
    photos = photos,
    places = emptyList(),
    history = CityHistory(emptyList())
)