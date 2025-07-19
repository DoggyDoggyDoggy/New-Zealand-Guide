package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.WeatherEntity
import denys.diomaxius.newzealandguide.domain.model.weather.Weather

fun WeatherEntity.toDomain(): Weather =
    Weather(
        temperature = temperature,
        description = description,
        iconUrl = iconUrl,
        dtTxt = dtTxt
    )