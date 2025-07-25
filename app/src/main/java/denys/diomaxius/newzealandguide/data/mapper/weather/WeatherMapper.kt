package denys.diomaxius.newzealandguide.data.mapper.weather

import denys.diomaxius.newzealandguide.data.model.weather.WeatherEntity
import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherEntity.toDomain(): Weather =
    Weather(
        temperature = temp,
        description = descr,
        icon = icon,
        dateTime = LocalDateTime.parse(
            dt_txt,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
    )