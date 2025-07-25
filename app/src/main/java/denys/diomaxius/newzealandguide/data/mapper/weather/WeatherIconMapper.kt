package denys.diomaxius.newzealandguide.data.mapper.weather

import denys.diomaxius.newzealandguide.data.model.weather.WeatherIconEntity
import denys.diomaxius.newzealandguide.domain.model.weather.WeatherIcon

fun WeatherIconEntity.toDomain(): WeatherIcon =
    WeatherIcon(
        iconId = iconId,
        iconUrl = iconUrl
    )