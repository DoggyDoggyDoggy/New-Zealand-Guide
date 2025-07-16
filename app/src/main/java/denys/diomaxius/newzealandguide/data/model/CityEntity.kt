package denys.diomaxius.newzealandguide.data.model

data class CityEntity(
    val id: String = "",
    val name: String = "",
    val photos: List<String> = emptyList()
)