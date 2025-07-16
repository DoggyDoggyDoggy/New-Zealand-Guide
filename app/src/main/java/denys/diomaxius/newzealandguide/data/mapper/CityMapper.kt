package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityEntity
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory

fun CityEntity.toDomain() : City = City(
    name = this.name,
    photos = this.photos,
    places = emptyList(),
    history = CityHistory(emptyList())
)