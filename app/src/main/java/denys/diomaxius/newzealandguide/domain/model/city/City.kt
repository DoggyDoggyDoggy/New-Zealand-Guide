package denys.diomaxius.newzealandguide.domain.model.city

data class City(
    val name: String,
    val photos: List<String>,
    val places: List<CityPlaceTopic>,
    val history: CityHistory
)