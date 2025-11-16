package denys.diomaxius.newzealandguide.data.local.room.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun CityWeatherEntity.toDomain() : CityWeather =
    CityWeather(
        cityId = cityId,
        temp = temp,
        description = description,
        icon = icon,
        dateTime = LocalDateTime.parse(
            dateTime,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
    )