package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun CityHistoryScreen(
    viewModel: CityHistoryScreenViewModel = hiltViewModel(),
    cityName: String,
) {
    val cityHistoryUiState by viewModel.cityHistoryUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(cityHistoryUiState) { cityHistory ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "History of $cityName",
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
                cityHistory = cityHistory
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cityHistory: CityHistory,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        cityHistory.paragraphs.forEach {
            Text(
                text = it,
                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
    }
}