package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    modifier: Modifier,
    newZealandHistory: NewZealandHistory,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        item {  }
        items(
            newZealandHistory.paragraphs.size
        ) { index ->
            ParagraphBlock(
                paragraph = newZealandHistory.paragraphs[index],
                paragraphTitle = newZealandHistory.paragraphsTitles[index]
            )
        }
        item {  }
    }
}