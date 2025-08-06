package denys.diomaxius.newzealandguide.data.mapper.home

import denys.diomaxius.newzealandguide.data.model.home.HomeEntity
import denys.diomaxius.newzealandguide.domain.model.home.Home

fun HomeEntity.toDomain(): Home = Home(photos = photos)