package denys.diomaxius.newzealandguide.data.remote.model

import androidx.annotation.Keep

@Keep
data class CityEventDto(
    val address: String = "",
    val description: String = "",
    val eventId: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val sessions: List<String> = emptyList(),
    val url: String = ""
)