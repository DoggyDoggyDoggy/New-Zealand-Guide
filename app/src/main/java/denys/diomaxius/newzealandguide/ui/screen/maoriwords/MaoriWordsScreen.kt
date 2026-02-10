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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaoriWordsScreen(
    viewModel: MaoriWordsScreenViewModel = hiltViewModel(),
) {
    val maoriWords = viewModel.maoriWords

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.top_bar_maori_words),
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
            maoriWords = maoriWords
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    maoriWords: MaoriWords,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(maoriWords.words.size) { index->
            Word(
                maori = maoriWords.words.keys.elementAt(index),
                english = maoriWords.words.values.elementAt(index)
            )
        }
    }
}

@Composable
fun Word(
    maori: String,
    english: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = maori,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = english,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}