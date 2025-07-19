package denys.diomaxius.newzealandguide.data.model

data class WeatherEntity(
    val temperature: Double = 0.0,
    val description: String = "",
    val iconUrl: String = "",
    val dt_txt: String = ""
)