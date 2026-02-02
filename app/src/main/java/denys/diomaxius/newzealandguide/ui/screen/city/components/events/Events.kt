package denys.diomaxius.newzealandguide.ui.screen.city.components.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

@Composable
fun Events(
    events: LazyPagingItems<CityEvent>,
    onClick: (cityId: String, eventId: String) -> Unit,
    hasInternetConnection: Boolean,
) {
    if (events.itemCount > 0) {
        Content(events, onClick)
    }

    if (events.loadState.refresh is LoadState.Loading && events.itemCount == 0) {
        EventsLoadingRow()
    }

    if (events.loadState.refresh is LoadState.Error && events.itemCount == 0) {
        if (!hasInternetConnection) {
            Text(
                text = "You are offline. Check your settings.",
                color = MaterialTheme.colorScheme.error,
            )
        } else {
            Button(
                onClick = {events.retry()}
            ) {
                Text("Retry")
            }
        }
    }
}

@Composable
fun Content(
    events: LazyPagingItems<CityEvent>,
    onClick: (String, String) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState
    ) {
        items(
            count = events.itemCount,
            key = events.itemKey { it.eventId },
            contentType = { "event_item" }
        ) { index ->
            events[index]?.let {
                CityEventCard(it, onClick)
            }
        }

        if (events.loadState.append is LoadState.Loading) {
            item {
                EventLoadingCard()
            }
        }
    }
}

@Composable
fun CityEventCard(
    event: CityEvent,
    onClick: (String, String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(175.dp)
            .clickable {
                onClick(event.cityId, event.eventId)
            },
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