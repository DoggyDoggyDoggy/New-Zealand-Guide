package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Card(
        modifier = modifier.padding(12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(12.dp)
        ) {
            items(
                newZealandHistory.paragraphs.size
            ) { index ->
                ParagraphBlock(
                    paragraph = newZealandHistory.paragraphs[index],
                    paragraphTitle = newZealandHistory.paragraphsTitles[index]
                )
                if (
                    index != newZealandHistory.paragraphs.size - 1
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun ParagraphBlock(
    paragraph: String,
    paragraphTitle: String,
) {
    Text(
        text = paragraphTitle,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

    Text(
        text = paragraph
    )
}