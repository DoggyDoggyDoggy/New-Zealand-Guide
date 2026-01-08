package denys.diomaxius.newzealandguide.domain.model.city

data class CityEvent(
    val cityId: String,
    val eventId: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val imageUrl: String,
    val sessions: List<String>,
    val positionInList: Int
)
