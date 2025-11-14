package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace

fun CityPlaceEntity.toDomain() : CityPlace =
    CityPlace(
        cityId = cityId,
        name = name,
        image = image,
        description = description
    )