package denys.diomaxius.newzealandguide.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.infocard.LongInfoCard

@Composable
fun NavigationMenu(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    LongCardNavigationMenu(
        modifier = modifier,
        navHostController = navHostController
    )
}

@Composable
fun LongCardNavigationMenu(
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
            titleCardText = stringResource(R.string.top_cities_towns),
            subTitleCardText = stringResource(R.string.top_cities_towns_subtitle),
            onClick = {
                navHostController.navigate(NavScreen.AllCities.route) {
                    launchSingleTop = true
                }
            },
            image = R.drawable.ic_building_outline
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = stringResource(R.string.te_reo_maori),
            subTitleCardText = stringResource(R.string.te_reo_maori_subtitle),
            onClick = {
                navHostController.navigate(NavScreen.MaoriWords.route) {
                    launchSingleTop = true
                }
            },
            image = R.drawable.ic_chat_outline
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = stringResource(R.string.nz_history_heritage),
            subTitleCardText = stringResource(R.string.nz_history_heritage_subtitle),
            onClick = {},
            image = R.drawable.ic_scroll_outline
        )

        LongInfoCard(
            modifier = Modifier,
            titleCardText = stringResource(R.string.fun_facts_tips),
            subTitleCardText = stringResource(R.string.fun_facts_tips_subtitle),
            onClick = {
                navHostController.navigate(NavScreen.NewZealandFacts.route) {
                    launchSingleTop = true
                }
            },
            image = R.drawable.ic_leaf_outline
        )
    }
}