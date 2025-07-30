package denys.diomaxius.newzealandguide.ui.screen.event

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    UiStateHandler(uiState) { event ->
        Content(event = event)
    }
}

@Composable
fun Content(event: Event) {
    Text(
        text = event.name
    )
}