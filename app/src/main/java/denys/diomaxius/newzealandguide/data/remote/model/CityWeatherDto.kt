package denys.diomaxius.newzealandguide.data.remote.model

import androidx.annotation.Keep

@Keep
data class CityWeatherDto(
    val temp: Double = 0.0,
    val descr: String = "",
    val icon: String = "",
    val dt_txt: String = "",
)
