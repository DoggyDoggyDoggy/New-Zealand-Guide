package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewZealandHistoryScreen(
    viewModel: NewZealandHistoryScreenViewModel = hiltViewModel(),
) {
    val newZealandHistory = viewModel.newZealandHistory

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.top_bar_nz_history),
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.navigateUp()
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

@Composable
fun Content(
    modifier: Modifier = Modifier,
    newZealandHistory: NewZealandHistory,
) {
    Card(
        modifier = modifier.padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp)
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