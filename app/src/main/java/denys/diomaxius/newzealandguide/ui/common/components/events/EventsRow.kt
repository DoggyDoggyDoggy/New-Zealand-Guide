package denys.diomaxius.newzealandguide.ui.common.components.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.event.Event

@Composable
fun EventsRow(
    viewModel: EventsRowViewModel = hiltViewModel(),
    onClick: (cityId: String, eventId: String) -> Unit,
    cityId: String,
) {
    val eventsUiState by viewModel.uiState.collectAsState()

    UiStateHandler(
        eventsUiState,
        loading = { EventsRowLoading() }
    ) { events ->
        Content(
            events = events,
            onClick = onClick,
            cityId = cityId
        )
    }
}

@Composable
fun Content(
    events: List<Event>,
    onClick: (cityId: String, eventId: String) -> Unit,
    cityId: String,
) {
    LazyRow {
        items(events) { event ->
            EventCard(
                event = event,
                onClick = onClick,
                cityId = cityId
            )
        }
    }
}

@Composable
fun EventCard(
    event: Event,
    onClick: (cityId: String, eventId: String) -> Unit,
    cityId: String,
) {
    Card(
        modifier = Modifier
            .padding(start = 12.dp)
            .size(175.dp)
            .clickable {
                onClick(cityId, event.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
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