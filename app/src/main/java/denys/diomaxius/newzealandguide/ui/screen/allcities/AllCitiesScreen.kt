package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.CityCard
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.EmptyFavoriteScreen
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.FavoriteFilter

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel(),
) {
    val allCities by viewModel.allCities.collectAsState()
    val favoriteCities by viewModel.favoriteCities.collectAsState()

    val favoriteFilter by viewModel.favoriteFilter.collectAsState()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.top_bar_all_cities),
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.navigateUp()
                    }
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            allCities = allCities,
            favoriteCities = favoriteCities,
            navHostController = navHostController,
            toggleFavorite = viewModel::toggleFavorite,
            favoriteFilter = favoriteFilter,
            toggleFavoriteFilter = viewModel::toggleFavoriteFilter,
            allListState = viewModel.allListState,
            favoriteListState = viewModel.favoriteListState,
        )
    }
}

@Composable
fun Content(
    modifier: Modifier,
    navHostController: NavHostController,
    toggleFavorite: (String) -> Unit,
    favoriteFilter: Boolean,
    toggleFavoriteFilter: () -> Unit,
    allCities: List<City>,
    favoriteCities: List<City>,
    favoriteListState: LazyListState,
    allListState: LazyListState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        FavoriteFilter(
            showFavorite = favoriteFilter,
            toggleFavorite = toggleFavoriteFilter
        )

        AnimatedContent(
            targetState = favoriteFilter,
            label = "list_transition"
        ) { showFavorites ->
            val (currentCities, currentState) = if (showFavorites) {
                favoriteCities to favoriteListState
            } else {
                allCities to allListState
            }

            if (currentCities.isEmpty() && showFavorites) {
                EmptyFavoriteScreen()
            } else {
                LazyColumn(
                    state = currentState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = currentCities,
                        key = { it.id }
                    ) { city ->
                        AnimatedCityCard(
                            city = city,
                            onClick = {
                                navHostController.navigate(
                                    NavScreen.City.createRoute(city.id)
                                ) {
                                    launchSingleTop = true
                                }
                            },
                            onFavoriteClick = { toggleFavorite(city.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedCityCard(
    city: City,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val visibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(800)
        )
    ) {
        CityCard(
            city = city,
            navigateToCity = onClick,
            toggleFavorite = onFavoriteClick
        )
    }
}