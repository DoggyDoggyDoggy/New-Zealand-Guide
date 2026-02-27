package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.FactCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.HeroBlock
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.NavigationCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.WordOfTheDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaoriHubScreen(
    viewModel: MaoriHubScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current

    val randomNZFact = viewModel.randomNzFact
    val wordOfTheDay = viewModel.wordOfTheDay

    Scaffold(
        topBar = {
            TopBar(
                text = "Te Reo Māori",
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.navigateUp()
                    }
                }
            )
        }
    ) {
        Content(
            modifier = Modifier.padding(it),
            navHostController = navHostController,
            randomNZFact = randomNZFact,
            wordOfTheDay = wordOfTheDay
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    randomNZFact: String,
    wordOfTheDay: Pair<String, String>,
) {
    val horizontalPadding = 16.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeroBlock(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = horizontalPadding / 2)
                .height(240.dp)
        )

        WordOfTheDay(
            modifier = Modifier.padding(horizontal = horizontalPadding),
            wordOfTheDay = wordOfTheDay
        )

        NavigationCard(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .height(110.dp),
            name = "Kupu",
            description = "Common maori words",
            onClick = {
                navHostController.navigate(NavScreen.MaoriWords.route) {
                    launchSingleTop = true
                }
            },
            icon = painterResource(id = R.drawable.outline_dictionary_24)
        )

        NavigationCard(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .height(110.dp),
            name = "Learning resources",
            description = "Articles & Links",
            onClick = {
                navHostController.navigate(NavScreen.MaoriLearningResources.route) {
                    launchSingleTop = true
                }
            },
            icon = painterResource(id = R.drawable.outline_newsstand_24)
        )

        FactCard(
            modifier = Modifier.padding(horizontal = horizontalPadding),
            fact = randomNZFact
        )
    }
}