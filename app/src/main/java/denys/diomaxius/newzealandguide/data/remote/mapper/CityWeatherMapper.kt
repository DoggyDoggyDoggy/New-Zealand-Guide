package denys.diomaxius.newzealandguide.data.remote.mapper

import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto

fun CityWeatherDto.toEntity(cityId: String): CityWeatherEntity =
    CityWeatherEntity(
        cityId = cityId,
        temp = temp,
        description = descr,
        icon = icon,
        dateTime = dt_txt,
    )