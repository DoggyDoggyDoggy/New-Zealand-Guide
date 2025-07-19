package denys.diomaxius.newzealandguide.data.model

// Maybe rename dt_txt to dtTxt
// dt_txt in firestore
// don't want to make in mutable

data class WeatherEntity(
    val temperature: Double = 0.0,
    val description: String = "",
    val iconUrl: String = "",
    val dt_txt: String = "",
)