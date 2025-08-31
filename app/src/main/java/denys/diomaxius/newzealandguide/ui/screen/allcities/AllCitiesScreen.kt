package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
                toggleFavorite = viewModel::toggleFavorite
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
    toggleFavorite: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Filters(
            showFavorite = showFavorite,
            toggleFavorite = toggleFavorite
        )

        LazyColumn(
            modifier = Modifier
        ) {
            items(cities) { city ->
                CityCard(
                    city = city,
                    navigateToCity = {
                        navHostController.navigate(
                            NavScreen.City.createRoute(city.id)
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Filters(
    modifier: Modifier = Modifier,
    showFavorite: Boolean,
    toggleFavorite: () -> Unit
) {
    Row(
        modifier = modifier.padding(start = 12.dp)
    ) {
        FilterChip(
            selected = !showFavorite,
            onClick = toggleFavorite,
            label = { Text(text = "All") }
        )

        Spacer(modifier = Modifier.width(12.dp))

        FilterChip(
            selected = showFavorite,
            onClick = toggleFavorite,
            label = { Text(text = "Favorite") }
        )
    }
}