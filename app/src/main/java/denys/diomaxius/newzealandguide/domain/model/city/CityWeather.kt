package denys.diomaxius.newzealandguide.domain.model.city

data class CityWeather(
    val cityId: String,
    val temp: Double,
    val description: String,
    val icon: String,
    val date: String,
)