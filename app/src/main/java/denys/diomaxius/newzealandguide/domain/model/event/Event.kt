package denys.diomaxius.newzealandguide.domain.model.event

data class Event(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val address: String,
    val sessions: List<Session>,
    val imageUrl: String
)

data class Session(
    val dateTimeSummary: String
)