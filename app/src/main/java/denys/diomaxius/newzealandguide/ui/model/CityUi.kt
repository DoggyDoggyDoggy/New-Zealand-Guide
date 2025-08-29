package denys.diomaxius.newzealandguide.ui.model

data class CityUi(
    val id: String,
    val name: String,
    val photos: List<String>,
    val isFavorite: Boolean,
)
