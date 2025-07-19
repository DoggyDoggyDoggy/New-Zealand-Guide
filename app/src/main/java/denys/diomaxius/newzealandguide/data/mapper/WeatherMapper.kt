package denys.diomaxius.newzealandguide.data.mapper

import denys.diomaxius.newzealandguide.data.model.WeatherEntity
import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherEntity.toDomain(): Weather =
    Weather(
        temperature = temperature,
        description = description,
        iconUrl = iconUrl,
        dateTime = LocalDateTime.parse(
            dtTxt,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
    )