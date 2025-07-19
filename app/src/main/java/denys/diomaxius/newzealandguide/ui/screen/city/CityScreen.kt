package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler
import denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider.CityPhotoSlider
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.components.weather.WeatherForecastFiveDays

@Composable
fun CityScreen(
    viewModel: CityScreenViewModel = hiltViewModel(),
) {
    val cityUiState by viewModel.cityUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(cityUiState) { city->
        Scaffold(
            topBar = {
                TopBar(
                    text = city.name,
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
                city = city,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    city: City,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CityPhotoSlider(
            modifier = Modifier
                .shadow(12.dp),
            photos = city.photos
        )

        WeatherForecastFiveDays()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            InfoCard(
                modifier = Modifier.weight(1f),
                cardText = "Top Things to Do in ${city.name} City",
                onClick = {
                    navHostController.navigate(
                        NavScreen.CityPlaces.createRoute(city.id, city.name)
                    ) {
                        launchSingleTop = true
                    }
                }
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            InfoCard(
                modifier = Modifier.weight(1f),
                cardText = "City History",
                onClick = {
                    navHostController.navigate(
                        NavScreen.CityHistory.createRoute(city.id, city.name)
                    ) {
                        launchSingleTop = true
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(165.dp)
            )
        }
    }
}