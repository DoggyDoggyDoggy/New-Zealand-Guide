package denys.diomaxius.newzealandguide.ui.screen.event

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventAddress
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventDates
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventBottomBar
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventDescription
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navHostController = LocalNavController.current
    val context: Context = LocalContext.current

    UiStateHandler(
        state = uiState,
        loading = { ScreenLoading() },
    ) { event ->
        Scaffold(
            topBar = {
                TopBar(
                    text = stringResource(R.string.event_top_bar),
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.navigateUp()
                        }
                    }
                )
            },
            bottomBar = {
                EventBottomBar(
                    modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
                    event = event,
                    context = context
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                event = event
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier,
    event: CityEvent,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        EventHeader(
            name = event.name,
            image = event.imageUrl
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        EventDescription(
            description = event.description
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        EventAddress(
            address = event.address
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        EventDates(
            eventSession = event.sessions
        )
    }
}