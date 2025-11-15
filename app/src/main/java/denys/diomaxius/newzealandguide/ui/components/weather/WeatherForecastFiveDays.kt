package denys.diomaxius.newzealandguide.ui.components.weather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler

@Composable
fun WeatherForecastFiveDays(
    viewModel: WeatherForecastFiveDaysViewModel = hiltViewModel()
) {
    val cityWeatherUiState by viewModel.uiState.collectAsState()

    UiStateHandler(
        cityWeatherUiState,
        loading = {}
    ) {
        Text(it[0].temp.toString())
    }
}