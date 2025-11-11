package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.cityhistory.CityHistory
import denys.diomaxius.newzealandguide.domain.model.cityhistory.CityHistoryEntity

fun CityHistoryEntity.toDomain(): CityHistory =
    CityHistory(
        cityId = cityId,
        paragraphs = paragraphs
    )