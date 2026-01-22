package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.screen.allcities.components.CityCard

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel(),
) {
    val cities = viewModel.lazyCities.collectAsLazyPagingItems()

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "All cities",
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
            cities = cities,
            navHostController = navHostController,
            toggleFavorite = viewModel::toggleFavorite
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cities: LazyPagingItems<City>,
    navHostController: NavHostController,
    toggleFavorite: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cities.itemCount) { index ->
            cities[index]?.let { city ->
                CityCard(
                    city = city,
                    navigateToCity = {
                        navHostController.navigate(
                            NavScreen.City.createRoute(city.id)
                        ) {
                            launchSingleTop = true
                        }
                    },
                    toggleFavorite = { toggleFavorite(city.id) }
                )
            }
        }
    }
}