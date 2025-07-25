package denys.diomaxius.newzealandguide.data.mapper.city

import denys.diomaxius.newzealandguide.data.model.city.CityPlaceTopicEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic

fun CityPlaceTopicEntity.toDomain(): CityPlaceTopic = CityPlaceTopic(
    title = title,
    paragraph = paragraph,
    image = image
)