package denys.diomaxius.newzealandguide.ui.screen.city.components.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeatherForecastFiveDays(weatherUiState: UiState<List<CityWeather>>) {
    UiStateHandler(
        weatherUiState,
        loading = { WeatherForecastFiveDaysLoading() },
    ) {weatherForecast ->
        Content(weatherForecast)
    }
}

@Composable
fun Content(weatherForecast: List<CityWeather>) {
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
    weather: CityWeather
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(dateFormatter(weather.dateTime))

            AsyncImage(
                model = "file:///android_asset/weathericons/${weather.icon}.png",
                contentDescription = "Icon"
            )

            Text(
                text = "${weather.temp.toInt()} °C"
            )
        }
    }
}

fun dateFormatter(dateTime: LocalDateTime): String {
    val formatterOut = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)
    val result = dateTime.format(formatterOut)
    return result
}