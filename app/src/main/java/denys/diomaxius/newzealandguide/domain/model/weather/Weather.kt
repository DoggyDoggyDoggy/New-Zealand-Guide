package denys.diomaxius.newzealandguide.domain.model.weather

import java.time.LocalDateTime

data class Weather(
    val temperature: Double,
    val description: String,
    val icon: String,
    val dateTime: LocalDateTime
)