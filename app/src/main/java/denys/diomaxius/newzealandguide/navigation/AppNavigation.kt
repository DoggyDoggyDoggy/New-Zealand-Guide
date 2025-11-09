package denys.diomaxius.newzealandguide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.newzealandguide.ui.screen.allcities.AllCitiesScreen
import denys.diomaxius.newzealandguide.ui.screen.home.HomeScreen
import denys.diomaxius.newzealandguide.ui.screen.maoriwords.MaoriWordsScreen
import denys.diomaxius.newzealandguide.ui.screen.nzfacts.NewZealandFactsScreen

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
            startDestination = NavScreen.HomeScreen.route
        ) {
            composable(NavScreen.HomeScreen.route) {
                HomeScreen()
            }

            composable(NavScreen.AllCitiesScreen.route) {
                AllCitiesScreen()
            }

            composable(NavScreen.NewZealandFacts.route) {
                NewZealandFactsScreen()
            }

            composable(NavScreen.MaoriWords.route) {
                MaoriWordsScreen()
            }
        }
    }
}