package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.CityPlaceTopicEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic

fun CityPlaceTopicEntity.toDomain(): CityPlaceTopic = CityPlaceTopic(
    title = title,
    paragraph = paragraph,
    image = image
)