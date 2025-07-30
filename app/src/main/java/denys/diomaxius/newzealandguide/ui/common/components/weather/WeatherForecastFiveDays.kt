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
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeatherForecastFiveDays(
    viewModel: WeatherForecastFiveDaysViewModel = hiltViewModel(),
) {
    val weatherForecastUiState by viewModel.uiState.collectAsState()

    UiStateHandler(weatherForecastUiState) { weatherForecast ->
        Content(weatherForecast)
    }
}

@Composable
fun Content(weatherForecast: List<WeatherUiModel>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        weatherForecast.forEach { weather ->
            WeatherForecastCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),
                weather = weather
            )
        }
    }
}

@Composable
fun WeatherForecastCard(
    modifier: Modifier = Modifier,
    weather: WeatherUiModel,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(dateFormatter(weather.dateTime))

            AsyncImage(
                model = weather.iconUrl,
                contentDescription = "Icon"
            )

            Text(
                text = "${weather.temperature.toInt()} °C"
            )
        }
    }
}

fun dateFormatter(dateTime: LocalDateTime): String {
    val formatterOut = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)
    val result = dateTime.format(formatterOut)
    return result
}