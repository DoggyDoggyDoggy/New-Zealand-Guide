package denys.diomaxius.newzealandguide.ui.components.events

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

@Composable
fun Events(
    viewModel: EventsViewModel = hiltViewModel()
) {
    val events = viewModel.events.collectAsLazyPagingItems()

    LazyColumn {
        items(
            events.itemCount
        ) {index->
            events[index]?.let {
                EventItem(it)
            }
        }
    }
}

@Composable
fun EventItem(event: CityEvent) {
    Text(
        modifier = Modifier.padding(6.dp),
        text = event.name
    )
}