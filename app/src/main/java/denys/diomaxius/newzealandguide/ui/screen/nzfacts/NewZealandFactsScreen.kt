package denys.diomaxius.newzealandguide.ui.screen.nzfacts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar

@Composable
fun NewZealandFactsScreen(
    viewModel: NewZealandFactsScreenViewModel = hiltViewModel()
) {
    val newZealandFacts = viewModel.newZealandFacts

    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.top_bar_nz_facts),
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
            newZealandFacts = newZealandFacts
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    newZealandFacts: NewZealandFacts,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            newZealandFacts.facts.size
        ) { index ->
            Fact(
                fact = newZealandFacts.facts[index]
            )
        }
    }
}

@Composable
fun Fact(
    fact: String
) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = fact
        )
    }
}