package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.FactCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.HeroBlock
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.NavigationCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.WordOfTheDay

@Composable
fun MaoriHubScreen(
    viewModel: MaoriHubScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current

    val randomNZFact = viewModel.randomNzFact

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
            randomNZFact = randomNZFact
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    randomNZFact: String
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeroBlock()

        WordOfTheDay()

        NavigationCard(
            name = "Kupu",
            description = "Common maori words"
        )

        NavigationCard(
            name = "Learning resources",
            description = "Articles & Links"
        )

        FactCard(
            fact = randomNZFact
        )
    }
}