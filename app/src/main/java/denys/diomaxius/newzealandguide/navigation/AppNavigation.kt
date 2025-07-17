package denys.diomaxius.newzealandguide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.newzealandguide.ui.allcities.AllCitiesScreen
import denys.diomaxius.newzealandguide.ui.cityplaces.CityPlacesScreen
import denys.diomaxius.newzealandguide.ui.city.CityScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not initialized")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    CompositionLocalProvider(LocalNavController provides navHostController) {
        NavHost(
            navController = navHostController,
            startDestination = NavScreen.Home.route
        ) {
            composable(NavScreen.Home.route) {
                AllCitiesScreen()
            }

            composable(NavScreen.City.route) {
                CityScreen()
            }

            composable(NavScreen.CityPlaces.route) {
                CityPlacesScreen()
            }
        }
    }
}