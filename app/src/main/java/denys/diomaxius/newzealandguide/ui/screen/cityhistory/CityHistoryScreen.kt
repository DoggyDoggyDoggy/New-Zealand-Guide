package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityHistoryScreen(
    viewModel: CityHistoryScreenViewModel = hiltViewModel(),
    cityName: String,
) {
    val uiState by viewModel.uiState.collectAsState()

    val navHostController = LocalNavController.current

    UiStateHandler(
        state = uiState,
        loading = { ScreenLoading() }
    )
    { cityHistory ->
        Scaffold(
            topBar = {
                TopBar(
                    text = stringResource(R.string.top_bar_city_history, cityName),
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
    Card(
        modifier = modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
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
}