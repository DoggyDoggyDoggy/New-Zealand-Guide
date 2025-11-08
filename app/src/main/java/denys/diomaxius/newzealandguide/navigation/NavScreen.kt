package denys.diomaxius.newzealandguide.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen: NavScreen("home_screen")
    object AllCitiesScreen : NavScreen("all_cities_screen")
}