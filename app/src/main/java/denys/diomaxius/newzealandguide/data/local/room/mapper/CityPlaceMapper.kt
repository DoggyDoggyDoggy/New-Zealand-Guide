package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.cityplace.CityPlaceEntity
import denys.diomaxius.newzealandguide.domain.model.cityplace.CityPlace

fun CityPlaceEntity.toDomain() : CityPlace =
    CityPlace(
        cityId = cityId,
        name = name,
        image = image,
        description = description
    )