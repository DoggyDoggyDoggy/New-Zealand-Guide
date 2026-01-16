package denys.diomaxius.newzealandguide.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home_screen")

    object AllCities : NavScreen("all_cities_screen")

    object NewZealandFacts : NavScreen("new_zealand_facts")

    object MaoriWords : NavScreen("maori_words")

    object NewZealandHistory : NavScreen("new_zealand_history")

    object City : NavScreen("city/{cityId}") {
        fun createRoute(cityId: String): String = "city/$cityId"
    }

    object CityHistory : NavScreen("cityhistory/{cityId}/{cityName}") {
        fun createRoute(cityId: String, cityName: String): String = "cityhistory/$cityId/$cityName"
    }

    object CityPlaces : NavScreen("cityplaces/{cityId}/{cityName}") {
        fun createRoute(cityId: String, cityName: String): String = "cityplaces/$cityId/$cityName"
    }

    object Event : NavScreen("event/{cityId}/{eventId}") {
        fun createRoute(cityId: String, eventId: String): String = "event/$cityId/$eventId"
    }
}