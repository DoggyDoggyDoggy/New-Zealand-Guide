package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.cityphotoslider.CityPhotoSlider
import denys.diomaxius.newzealandguide.ui.screen.city.components.events.Events
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.city.components.ColumnOfTwoLongInfoCards
import denys.diomaxius.newzealandguide.ui.screen.city.components.weather.WeatherForecastFiveDays
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading

@Composable
fun CityScreen(
    viewModel: CityScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current

    val uiState by viewModel.uiState.collectAsState()

    //Change to UiState later on
    UiStateHandler(
        state = uiState,
        loading = { ScreenLoading() }
    ) { city ->
        Scaffold(
            topBar = {
                TopBar(
                    text = city.name,
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
            modifier = Modifier.height(16.dp)
        )

        WeatherForecastFiveDays()

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Events(
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

        ColumnOfTwoLongInfoCards(
            modifier = Modifier.padding(horizontal = 6.dp),
            navHostController = navHostController,
            city = city
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}