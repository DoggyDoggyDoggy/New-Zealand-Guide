package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.FactCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.HeroBlock
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.NavigationCard
import denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components.WordOfTheDay

@Composable
fun MaoriHubScreen() {
    val navHostController = LocalNavController.current

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
        Content(modifier = Modifier.padding(it))
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
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
            fact = ""
        )
    }
}