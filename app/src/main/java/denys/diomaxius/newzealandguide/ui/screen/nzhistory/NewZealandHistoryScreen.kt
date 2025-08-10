package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler

@Composable
fun NewZealandHistoryScreen(
    viewModel: NewZealandHistoryScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current
    val uiState by viewModel.nzHistoryUiState.collectAsState()

    UiStateHandler(
        state = uiState,
        loading = {
            ScreenLoading()
        }
    ) { newZealandHistory ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "New Zealand History",
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
                newZealandHistory = newZealandHistory
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    newZealandHistory: NewZealandHistory,
) {
    Column(
        modifier = modifier.padding()
    ) {
        Text(
            text = newZealandHistory.paragraphs[0]
        )
    }
}