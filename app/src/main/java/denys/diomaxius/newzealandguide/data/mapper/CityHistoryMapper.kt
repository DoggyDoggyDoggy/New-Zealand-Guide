package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityHistoryEntity

fun CityHistoryEntity.toDomain(): CityHistoryEntity =
    CityHistoryEntity(paragraphs = paragraphs)