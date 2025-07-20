package denys.diomaxius.newzealandguide.data.model

// Maybe rename dt_txt to dtTxt
// dt_txt in firestore
// don't want to make in mutable

//firestore
// temp, dt_txt, descr, icon

data class WeatherEntity(
    val temp: Double = 0.0,
    val descr: String = "",
    val icon: String = "",
    val dt_txt: String = "",
)