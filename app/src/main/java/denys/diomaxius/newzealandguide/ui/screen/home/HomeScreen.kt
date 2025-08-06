package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.InfoCard
import denys.diomaxius.newzealandguide.ui.common.components.topbar.MenuButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                text = "New Zealand Guide",
                navigationButton = {
                    MenuButton {}
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            navHostController = navHostController
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        HeroBlock()

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            InfoCard(
                modifier = Modifier.weight(1f),
                cardText = "New Zealand cities",
                onClick = {
                    navHostController.navigate(NavScreen.AllCities.route) {
                        launchSingleTop = true
                    }
                }
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            InfoCard(
                modifier = Modifier.weight(1f),
                cardText = "Maori Words",
                onClick = {

                }
            )
        }
    }
}

@Composable
fun HeroBlock(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kia ora!",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Explore New Zealand",
            style = MaterialTheme.typography.titleMedium
        )
    }
}