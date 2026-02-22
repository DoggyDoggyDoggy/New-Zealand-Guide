package denys.diomaxius.newzealandguide.data.utils

object CityIdMapper {
    private val cityMap = mapOf(
        "2" to "Auckland",
        "363" to "Wellington",
        "42" to "Hamilton",
        "110" to "Christchurch",
        "67" to "Napier",
        "126" to "Dunedin",
        "130" to "Queenstown",
        "137" to "Invercargill",
        "57" to "Rotorua",
        "58" to "Taupo",
        "59" to "Tauranga",
        "6" to "Gisborne",
        "7051" to "Whangarei",
        "72" to "New Plymouth",
        "83" to "Palmerston North",
    )

    fun getNameById(id: String?): String {
        return cityMap[id] ?: "Unknown City"
    }
}