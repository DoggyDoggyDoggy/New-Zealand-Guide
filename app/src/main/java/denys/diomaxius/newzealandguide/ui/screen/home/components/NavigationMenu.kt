package denys.diomaxius.newzealandguide.ui.screen.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.infocard.TwoInfoCardsRow

@Composable
fun NavigationMenu(
    navHostController: NavHostController
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