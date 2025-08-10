package denys.diomaxius.newzealandguide.ui.screen.nzfacts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler

@Composable
fun NewZealandFactsScreen(
    viewModel: NewZealandFactsScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current
    val uiState by viewModel.nzFactsUiState.collectAsState()

    UiStateHandler(
        state = uiState,
        loading = {
            ScreenLoading()
        }
    ) { newZealandFacts ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "New Zealand Facts",
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.popBackStack()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                newZealand = newZealandFacts
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    newZealand: NewZealandFacts,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            newZealand.facts.size
        ) { index ->
            Fact(
                fact = newZealand.facts[index]
            )
        }
    }
}

@Composable
fun Fact(
    fact: String
) {
    Card(
        modifier = Modifier.padding(6.dp)
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = fact
        )
    }
}