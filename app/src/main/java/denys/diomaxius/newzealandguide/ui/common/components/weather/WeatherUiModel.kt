package denys.diomaxius.newzealandguide.ui.common.components.weather

import java.time.LocalDateTime

data class WeatherUiModel(
    val dateTime: LocalDateTime,
    val temperature: Double,
    val iconUrl: String
)