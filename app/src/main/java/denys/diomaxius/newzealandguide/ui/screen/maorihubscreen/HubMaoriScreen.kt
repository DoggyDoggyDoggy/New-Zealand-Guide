package denys.diomaxius.playground.maoriwords

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

val fact = "There are still more sheep than people (though the ratio has fallen). New Zealand’s flock historically outnumbered humans by a large margin; recent stats still show multiple sheep per person."

@Composable
fun HubMaoriScreen() {
    Scaffold(
        topBar = {
            TopBar(
                text = "Te Reo Māori",
                navigationButton = {
                    PopBackArrowButton {}
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
            fact = fact
        )
    }
}