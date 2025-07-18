package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun CityPlacesScreen(
    viewModel: CityPlacesScreenViewModel = hiltViewModel(),
) {
    val cityPlacesUiState by viewModel.placesUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(cityPlacesUiState) { cityPlacesTopics ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "Top Things to Do in cityName",
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
                cityPlacesTopics = cityPlacesTopics
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cityPlacesTopics: List<CityPlaceTopic>,
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(cityPlacesTopics) {
            Text(it.title)
        }
    }
}