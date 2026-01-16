package denys.diomaxius.newzealandguide.ui.screen.event

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventAddress
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventBottomBar
import denys.diomaxius.newzealandguide.ui.screen.event.components.EventDescription

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val navHostController = LocalNavController.current
    val context: Context = LocalContext.current

    UiStateHandler(
        state = uiState
    ) { event ->
        Scaffold(
            topBar = {
                TopBar(
                    text = event.name,
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
    Card(
        modifier = modifier
            .padding(top = 12.dp)
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = event.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                model = event.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Image"
            )

            Spacer(modifier = Modifier.height(6.dp))

            EventDescription(event.description)

            Spacer(modifier = Modifier.height(12.dp))

            EventAddress(event.address)

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}