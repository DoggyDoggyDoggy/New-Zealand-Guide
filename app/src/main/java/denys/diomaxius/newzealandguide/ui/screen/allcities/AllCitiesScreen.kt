package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.TextOverlay
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.common.components.topbar.MenuButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel(),
) {
    val citiesUiState by viewModel.citiesUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(
        state = citiesUiState,
        loading = { LoadingAllCities() }
    ) { cities ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "City Guide",
                    navigationButton = {
                        MenuButton {
                            // TODO()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                navHostController = navHostController,
                cities = cities
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cities: List<City>,
    navHostController: NavHostController,
) {
    LazyColumn(
        modifier = modifier
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

@Composable
fun CityCard(
    city: City,
    navigateToCity: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                navigateToCity()
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = city.photos.first(),
                contentDescription = city.name,
                contentScale = ContentScale.FillWidth
            )

            TextOverlay(text = city.name)
        }
    }
}