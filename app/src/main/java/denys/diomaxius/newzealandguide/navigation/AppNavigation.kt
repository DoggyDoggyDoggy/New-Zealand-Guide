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
import denys.diomaxius.newzealandguide.ui.screen.city.CityScreen
import denys.diomaxius.newzealandguide.ui.screen.cityhistory.CityHistoryScreen
import denys.diomaxius.newzealandguide.ui.screen.home.HomeScreen
import denys.diomaxius.newzealandguide.ui.screen.maoriwords.MaoriWordsScreen
import denys.diomaxius.newzealandguide.ui.screen.nzfacts.NewZealandFactsScreen
import denys.diomaxius.newzealandguide.ui.screen.nzhistory.NewZealandHistoryScreen

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
                HomeScreen()
            }

            composable(NavScreen.AllCities.route) {
                AllCitiesScreen()
            }

            composable(NavScreen.NewZealandFacts.route) {
                NewZealandFactsScreen()
            }

            composable(NavScreen.MaoriWords.route) {
                MaoriWordsScreen()
            }

            composable(NavScreen.NewZealandHistory.route) {
                NewZealandHistoryScreen()
            }

            composable(NavScreen.City.route) {
                CityScreen()
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
        }
    }
}