package denys.diomaxius.newzealandguide.ui.screen.maoriwords

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun MaoriWordsScreen(
    viewModel: MaoriWordsScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current
    val uiState by viewModel.maoriWordsUiState.collectAsState()

    UiStateHandler(
        state = uiState,
        loading = {
            ScreenLoading()
        }
    ) { maoriWords ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "Common Maori Words",
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
                maoriWords = maoriWords
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    maoriWords: MaoriWords
) {
    val entries = remember(maoriWords.words) {
        maoriWords.words
            .toSortedMap()
            .entries
            .toList()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = entries,
            key = { it.key }
        ) { (maori, english) ->
            Word(
                maori = maori,
                english = english
            )
        }
    }
}

@Composable
fun Word(
    maori: String,
    english: String
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column (
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = maori,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )

            HorizontalDivider()

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = english,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}