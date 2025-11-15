package denys.diomaxius.newzealandguide.data.remote.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto

fun CityWeatherDto.toEntity() : CityWeatherEntity =
    CityWeatherEntity(
        cityId = "",
        temp = temp,
        description = descr,
        icon = icon,
        dateTime = dt_txt,
    )