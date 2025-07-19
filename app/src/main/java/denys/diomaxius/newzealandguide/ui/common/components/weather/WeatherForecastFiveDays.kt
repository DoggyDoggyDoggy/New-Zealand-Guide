package denys.diomaxius.newzealandguide.ui.common.components.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler

private const val ICON_BASE_URL = "https://openweathermap.org/img/wn/"

@Composable
fun WeatherForecastFiveDays(
    viewModel: WeatherForecastFiveDaysViewModel = hiltViewModel(),
) {
    val weatherForecastUiState by viewModel.weatherForecastUiState.collectAsState()

    UiStateHandler(weatherForecastUiState) { weatherForecast ->
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            weatherForecast.forEach {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(it.dateTime.toString())

                        AsyncImage(
                            model = ICON_BASE_URL + it.iconUrl + "@2x.png",
                            contentDescription = "Icon"

                        )

                        Text(
                            text = "${it.temperature.toInt()} °C"
                        )
                    }
                }
            }
        }
    }
}