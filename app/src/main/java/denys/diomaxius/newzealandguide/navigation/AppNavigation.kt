package denys.diomaxius.newzealandguide.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import denys.diomaxius.newzealandguide.ui.screen.allcities.AllCitiesScreen
import denys.diomaxius.newzealandguide.ui.screen.city.CityScreen
import denys.diomaxius.newzealandguide.ui.screen.cityhistory.CityHistoryScreen
import denys.diomaxius.newzealandguide.ui.screen.cityplaces.CityPlacesScreen
import denys.diomaxius.newzealandguide.ui.screen.event.EventDetailsScreen
import denys.diomaxius.newzealandguide.ui.screen.home.HomeScreen
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.MaoriHubScreen
import denys.diomaxius.newzealandguide.ui.screen.maorilearningresources.MaoriLearningResourcesScreen
import denys.diomaxius.newzealandguide.ui.screen.maoriwords.MaoriWordsScreen
import denys.diomaxius.newzealandguide.ui.screen.nzhistory.NewZealandHistoryScreen

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
            startDestination = NavScreen.Home.route
        ) {
            composable(
                route = NavScreen.Home.route,
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn() + slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    scaleOut(targetScale = 1.2f) + fadeOut()
                }
            ) {
                HomeScreen()
            }

            composable(
                route = NavScreen.AllCities.route,
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn() + slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    scaleOut(targetScale = 1.2f) + fadeOut()
                }
            ) {
                AllCitiesScreen()
            }

            composable(
                route = NavScreen.MaoriHub.route,
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn() + slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    scaleOut(targetScale = 1.2f) + fadeOut()
                }
            ) {
                MaoriHubScreen()
            }

            composable(
                route = NavScreen.MaoriWords.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                MaoriWordsScreen()
            }

            composable(
                route = NavScreen.MaoriLearningResources.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                MaoriLearningResourcesScreen()
            }

            composable(
                route = NavScreen.NewZealandHistory.route,
                enterTransition = {
                    scaleIn(initialScale = 0.8f) + fadeIn() + slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    scaleOut(targetScale = 1.2f) + fadeOut()
                }
            ) {
                NewZealandHistoryScreen()
            }

            composable(
                route = NavScreen.City.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) }
            ) {
                CityScreen()
            }

            composable(
                route = NavScreen.CityHistory.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
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
                route = NavScreen.CityPlaces.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
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
                route = NavScreen.Event.route,
                arguments = listOf(
                    navArgument("cityId") { type = NavType.StringType },
                    navArgument("eventId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val progress by transition.animateFloat(
                    label = "RevealProgress",
                    transitionSpec = {
                        if (targetState == EnterExitState.Visible) {
                            tween(durationMillis = 500, easing = EaseInExpo)
                        } else {
                            tween(durationMillis = 800, easing = EaseOutExpo)
                        }
                    }
                ) { state ->
                    if (state == EnterExitState.Visible) 1f else 0f
                }

                EventDetailsScreen(
                    modifier = Modifier.circularReveal(progress)
                )
            }
        }
    }
}

fun Modifier.circularReveal(progress: Float): Modifier =
    this.graphicsLayer {
        clip = true
        shape = GenericShape { size, _ ->
            val center = Offset(size.width / 2, size.height / 2)
            val maxRadius = Math.hypot(size.width.toDouble(), size.height.toDouble()).toFloat()
            addOval(Rect(center, maxRadius * progress))
        }
    }