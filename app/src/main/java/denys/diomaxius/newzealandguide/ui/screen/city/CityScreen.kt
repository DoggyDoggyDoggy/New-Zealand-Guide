package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider.CityPhotoSlider
import denys.diomaxius.newzealandguide.ui.common.components.events.EventsRow
import denys.diomaxius.newzealandguide.ui.common.components.infocard.TwoInfoCardsRow
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.components.weather.WeatherForecastFiveDays

@Composable
fun CityScreen(
    viewModel: CityScreenViewModel = hiltViewModel(),
) {
    val cityUiState by viewModel.cityUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(
        state = cityUiState,
        loading = { ScreenLoading() }
    ) { city ->
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

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        WeatherForecastFiveDays()

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        EventsRow(
            cityId = city.id,
            onClick = { cityId, eventId ->
                navHostController.navigate(
                    NavScreen.Event.createRoute(cityId, eventId)
                ) {
                    launchSingleTop = true
                }
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        TwoInfoCardsRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            firstCardText = stringResource(R.string.attraction_in_city_card, city.name),
            secondCardText = stringResource(R.string.city_history_card),
            firstCardOnClick = {
                navHostController.navigate(
                    NavScreen.CityPlaces.createRoute(city.id, city.name)
                ) {
                    launchSingleTop = true
                }
            },
            secondCardOnClick = {
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