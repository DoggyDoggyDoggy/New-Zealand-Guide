package denys.diomaxius.newzealandguide.ui.common.components.events

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler
import androidx.compose.runtime.getValue
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.event.Event

@Composable
fun EventsRow(
    viewModel: EventsRowViewModel = hiltViewModel()
) {
    val eventsUiState by viewModel.uiState.collectAsState()
    UiStateHandler(eventsUiState) {events ->
        Content(
            events
        )
    }
}

@Composable
fun Content(events: List<Event>) {
    LazyRow {
        items(events.size) { index ->
            Card {
                AsyncImage(
                    model = events[index].imageUrl,
                    contentDescription = ""
                )
            }
        }
    }
}