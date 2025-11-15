package denys.diomaxius.newzealandguide.data.remote.model

data class CityWeatherDto(
    val temp: Double = 0.0,
    val descr: String = "",
    val icon: String = "",
    val dt_txt: String = "",
)
