package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity

fun CityHistoryEntity.toDomain(): denys.diomaxius.newzealandguide.domain.model.city.CityHistory =
    denys.diomaxius.newzealandguide.domain.model.city.CityHistory(
        cityId = cityId,
        paragraphs = paragraphs
    )