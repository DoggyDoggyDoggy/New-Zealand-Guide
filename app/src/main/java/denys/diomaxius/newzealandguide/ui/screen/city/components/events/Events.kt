package denys.diomaxius.newzealandguide.ui.screen.city.components.events

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

@Composable
fun Events(
    viewModel: EventsViewModel = hiltViewModel(),
) {
    val events = viewModel.events.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.height(200.dp)
    ) {
        items(
            count = events.itemCount,
            key = events.itemKey { it.eventId },
            contentType = { "event_item" }
        ) { index ->
            val event = events[index]
            if (event != null) {
                EventItem(event)
            }
        }
    }
}

@Composable
fun EventItem(event: CityEvent) {
    Log.i("Events", "Event: ${event.eventId}")

    Text(
        modifier = Modifier.padding(6.dp),
        text = event.name
    )
}