package denys.diomaxius.newzealandguide.data.mapper.city

import denys.diomaxius.newzealandguide.data.model.city.CityHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory

fun CityHistoryEntity.toDomain(): CityHistory =
    CityHistory(paragraphs = paragraphs)