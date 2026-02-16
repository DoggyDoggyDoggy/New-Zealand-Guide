package denys.diomaxius.newzealandguide.ui.screen.event

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
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

        AnimatedVisibility(
            visible = !expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
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
            }
        }

        EventDates(
            eventSession = event.sessions,
            expanded = expanded,
            toggleExpanded = { expanded = !expanded }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}