package denys.diomaxius.newzealandguide.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.infocard.LongInfoCard
import denys.diomaxius.newzealandguide.ui.common.components.infocard.TwoInfoCardsRow

@Composable
fun NavigationMenu(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Column(
        modifier = modifier.padding(horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LongInfoCard(
            modifier = Modifier,
            titleCardText = "Top Cities & Towns",
            subTitleCardText = "Explore New Zealand",
            onClick = {
                navHostController.navigate(NavScreen.AllCities.route) {
                    launchSingleTop = true
                }
            }
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = "Te Reo Māori",
            subTitleCardText = "Learn Te Reo",
            onClick = {
                navHostController.navigate(NavScreen.MaoriWords.route) {
                    launchSingleTop = true
                }
            }
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = "NZ History & Heritage",
            subTitleCardText = "Dive into History",
            onClick = {
                navHostController.navigate(NavScreen.NewZealandHistory.route) {
                    launchSingleTop = true
                }
            }
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = "Fun Facts & Tips",
            subTitleCardText = "Quick insights",
            onClick = {
                navHostController.navigate(NavScreen.NewZealandFacts.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}

@Composable
fun SquareNavigationMenu(
    navHostController: NavHostController,
) {
    TwoInfoCardsRow(
        modifier = Modifier
            .fillMaxWidth(),
        firstCardText = "Cities",
        secondCardText = "Maori Words",
        firstCardOnClick = {
            navHostController.navigate(NavScreen.AllCities.route) {
                launchSingleTop = true
            }
        },
        secondCardOnClick = {
            navHostController.navigate(NavScreen.MaoriWords.route) {
                launchSingleTop = true
            }
        }
    )

    Spacer(modifier = Modifier.height(12.dp))

    TwoInfoCardsRow(
        modifier = Modifier
            .fillMaxWidth(),
        firstCardText = "History",
        secondCardText = "Facts",
        firstCardOnClick = {
            navHostController.navigate(NavScreen.NewZealandHistory.route) {
                launchSingleTop = true
            }
        },
        secondCardOnClick = {
            navHostController.navigate(NavScreen.NewZealandFacts.route) {
                launchSingleTop = true
            }
        }
    )
}