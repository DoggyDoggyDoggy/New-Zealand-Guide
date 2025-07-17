package denys.diomaxius.newzealandguide.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object City : NavScreen("city/{cityId}") {
        fun createRoute(cityId: String): String = "city/$cityId"
    }

    object Event : NavScreen("event/{eventId}") {
        fun createRoute(eventId: String): String = "event/$eventId"
    }

    object CityPlaces : NavScreen("cityplaces/{cityId}") {
        fun createRoute(cityId: String): String = "cityplaces/$cityId"
    }

    object CityHistory : NavScreen("cityhistory/{cityId}") {
        fun createRoute(cityId: String): String = "cityhistory/$cityId"
    }
}