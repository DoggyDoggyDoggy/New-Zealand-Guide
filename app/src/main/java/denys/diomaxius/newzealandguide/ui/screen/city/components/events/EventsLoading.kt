package denys.diomaxius.newzealandguide.ui.screen.city.components.events

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.ui.components.shimmer.shimmer

@Composable
fun EventsLoadingRow() {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        repeat(4) {
            EventLoadingCard()
        }
    }
}

@Composable
fun EventLoadingCard() {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(175.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize().shimmer()
        )
    }
}