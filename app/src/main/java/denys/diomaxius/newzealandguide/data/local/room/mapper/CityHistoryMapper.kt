package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.cityhistory.CityHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.cityhistory.CityHistory

fun CityHistoryEntity.toDomain(): CityHistory =
    CityHistory(
        cityId = cityId,
        paragraphs = paragraphs
    )