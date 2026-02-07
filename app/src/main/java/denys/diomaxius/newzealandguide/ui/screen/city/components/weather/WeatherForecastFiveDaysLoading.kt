package denys.diomaxius.newzealandguide.ui.screen.city.components.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.ui.components.shimmer.shimmer

@Composable
fun WeatherForecastFiveDaysLoading() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(5) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp)
                    .height(82.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().shimmer()
                )
            }
        }
    }
}