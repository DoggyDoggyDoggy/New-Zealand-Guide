package denys.diomaxius.newzealandguide.ui.screen.city.components.events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

@Composable
fun Events(
    viewModel: EventsViewModel = hiltViewModel(),
) {
    val events = viewModel.events.collectAsLazyPagingItems()

    LazyRow {
        items(
            count = events.itemCount,
            key = events.itemKey { it.eventId },
            contentType = { "event_item" }
        ) { index ->
            val event = events[index]
            if (event != null) {
                CityEventCard(event)
            }
        }
    }
}

@Composable
fun CityEventCard(
    event: CityEvent,
) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(175.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.size(175.dp, 130.dp),
                model = event.imageUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Event image"
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 4.dp),
                    text = event.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
            }
        }
    }
}