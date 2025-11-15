package denys.diomaxius.newzealandguide.domain.model.city

import java.time.LocalDateTime

data class CityWeather(
    val cityId: String,
    val temp: Double,
    val description: String,
    val icon: String,
    val dateTime: LocalDateTime,
)