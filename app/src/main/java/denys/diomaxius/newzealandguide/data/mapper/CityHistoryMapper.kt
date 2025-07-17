package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory

fun CityHistoryEntity.toDomain(): CityHistory =
    CityHistory(paragraphs = paragraphs)