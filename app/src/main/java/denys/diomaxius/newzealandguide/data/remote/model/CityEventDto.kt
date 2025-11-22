package denys.diomaxius.newzealandguide.data.remote.model

data class CityEventDto(
    val cityId: String = "",
    val eventId: String = "",
    val url: String = "",
    val name: String = "",
    val description: String = "",
    val address: String = "",
    val imageUrl: String = "",
    val sessions: List<String> = emptyList()
)
