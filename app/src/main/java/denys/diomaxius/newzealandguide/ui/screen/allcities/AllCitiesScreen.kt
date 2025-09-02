package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.model.CityUi
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.EmptyFavoriteScreen
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.CityCard
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.Filters
import kotlinx.coroutines.delay

private const val EXIT_ANIMATION_MS = 260L

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel(),
) {
    val citiesUiState by viewModel.citiesUiState.collectAsState()
    val navHostController = LocalNavController.current
    val showFavorite by viewModel.showFavorite.collectAsState()

    UiStateHandler(
        state = citiesUiState,
        loading = { ScreenLoading() }
    ) { cities ->
        Scaffold(
            topBar = {
                TopBar(
                    text = stringResource(R.string.top_bar_all_cities),
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.popBackStack()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                navHostController = navHostController,
                cities = cities,
                showFavorite = showFavorite,
                toggleFavorite = viewModel::toggleFavorite,
                toggleCityFavorite = { cityId, currentlyFavorite ->
                    viewModel.toggleCityFavorite(cityId, currentlyFavorite)
                }
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cities: List<CityUi>,
    navHostController: NavHostController,
    showFavorite: Boolean,
    toggleFavorite: () -> Unit,
    toggleCityFavorite: (cityId: String, currentlyFavorite: Boolean) -> Unit,
) {
    val visibleMap = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(cities) {
        cities.forEach { city ->
            if (!visibleMap.containsKey(city.id)) visibleMap[city.id] = true
        }
    }

    if (showFavorite && cities.isEmpty()) {
        EmptyFavoriteScreen()
    }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {
        item {
            Filters(
                showFavorite = showFavorite,
                toggleFavorite = toggleFavorite
            )
        }
        items(
            items = cities,
            key = { it.id }
        ) { city ->
            val visible = visibleMap.getOrElse(city.id) { true }

            AnimatedVisibility(
                visible = visible,
                exit = slideOutHorizontally(
                    targetOffsetX = { -it }
                ) + fadeOut()
            ){
                CityCard(
                    modifier = Modifier
                        .animateItem(),
                    city = city,
                    navigateToCity = {
                        navHostController.navigate(
                            NavScreen.City.createRoute(city.id)
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onFavoriteClick = {
                        if (showFavorite && city.isFavorite) {
                            visibleMap[city.id] = false
                        } else {
                            toggleCityFavorite(city.id, city.isFavorite)
                        }
                    }
                )
            }
            LaunchedEffect(key1 = visible, key2 = city.id) {
                if (!visible) {
                    delay(EXIT_ANIMATION_MS + 20)
                    toggleCityFavorite(city.id, city.isFavorite)
                    visibleMap.remove(city.id)
                }
            }
        }
    }
}