package denys.diomaxius.newzealandguide.ui.screen.event

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.event.components.BuyTicketButton
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventAddress
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventDates
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventDescription
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventHeader
import denys.diomaxius.newzealandguide.ui.screen.event.components.shareEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navHostController = LocalNavController.current
    val context = LocalContext.current

    UiStateHandler(
        state = uiState,
        loading = { ScreenLoading() },
    ) { event ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "",
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.navigateUp()
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.toggleFavorite()
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource(R.drawable.outline_kid_star_24),
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = ""
                            )
                        }

                        IconButton(
                            onClick = {
                                shareEvent(context, event)
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource(R.drawable.outline_send_24),
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = ""
                            )
                        }
                    }
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
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
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
            modifier = Modifier.weight(1f)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        BuyTicketButton(
            event = event,
            context = LocalContext.current
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}