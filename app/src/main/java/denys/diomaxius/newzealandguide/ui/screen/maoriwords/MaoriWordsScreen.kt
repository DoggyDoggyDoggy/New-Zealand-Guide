package denys.diomaxius.newzealandguide.ui.screen.maoriwords

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(maori)
        Text(english)
    }
}