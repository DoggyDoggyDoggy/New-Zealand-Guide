package denys.diomaxius.newzealandguide.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")

    object MaoriWords : NavScreen("maoriwords")

    object NewZealandHistory : NavScreen("newzealandhistory")

    object AllCities : NavScreen("allcities")

    object City : NavScreen("city/{cityId}") {
        fun createRoute(cityId: String): String = "city/$cityId"
    }

    object Event : NavScreen("event/{cityId}/{eventId}") {
        fun createRoute(cityId: String, eventId: String): String = "event/$cityId/$eventId"
    }

    object CityPlaces : NavScreen("cityplaces/{cityId}/{cityName}") {
        fun createRoute(cityId: String, cityName: String): String = "cityplaces/$cityId/$cityName"
    }

    object CityHistory : NavScreen("cityhistory/{cityId}/{cityName}") {
        fun createRoute(cityId: String, cityName: String): String = "cityhistory/$cityId/$cityName"
    }
}