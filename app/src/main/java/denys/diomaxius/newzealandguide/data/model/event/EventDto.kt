package denys.diomaxius.newzealandguide.data.model.event

data class EventDto(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val sessions: List<SessionDto>,
    val imageUrl: String,
)