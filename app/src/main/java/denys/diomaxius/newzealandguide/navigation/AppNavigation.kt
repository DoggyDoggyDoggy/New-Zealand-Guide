package denys.diomaxius.newzealandguide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import denys.diomaxius.newzealandguide.ui.screen.allcities.AllCitiesScreen
import denys.diomaxius.newzealandguide.ui.screen.cityplaces.CityPlacesScreen
import denys.diomaxius.newzealandguide.ui.screen.city.CityScreen
import denys.diomaxius.newzealandguide.ui.screen.cityhistory.CityHistoryScreen
import denys.diomaxius.newzealandguide.ui.screen.event.EventDetailsScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not initialized")
}

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    CompositionLocalProvider(LocalNavController provides navHostController) {
        NavHost(
            navController = navHostController,
            startDestination = NavScreen.AllCities.route
        ) {
            composable(NavScreen.AllCities.route) {
                AllCitiesScreen()
            }

            composable(NavScreen.City.route) {
                CityScreen()
            }

            composable(
                route = NavScreen.CityPlaces.route,
                arguments = listOf(
                    navArgument("cityId") { type = NavType.StringType },
                    navArgument("cityName") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val cityName = backStackEntry.arguments!!.getString("cityName")!!

                CityPlacesScreen(
                    cityName = cityName
                )
            }

            composable(
                route = NavScreen.CityHistory.route,
                arguments = listOf(
                    navArgument("cityId") { type = NavType.StringType },
                    navArgument("cityName") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val cityName = backStackEntry.arguments!!.getString("cityName")!!

                CityHistoryScreen(
                    cityName = cityName
                )
            }

            composable(
                route = NavScreen.Event.route,
                arguments = listOf(
                    navArgument("cityId") { type = NavType.StringType },
                    navArgument("eventId") { type = NavType.StringType }
                )
            ) {
                EventDetailsScreen()
            }
        }
    }
}