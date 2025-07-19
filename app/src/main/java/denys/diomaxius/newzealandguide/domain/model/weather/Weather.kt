package denys.diomaxius.newzealandguide.domain.model.weather

data class Weather(
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val dtTxt: String
)