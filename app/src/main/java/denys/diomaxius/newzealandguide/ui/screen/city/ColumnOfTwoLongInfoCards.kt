package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.ui.components.infocard.LongInfoCard

@Composable
fun ColumnOfTwoLongInfoCards(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    city: City,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LongInfoCard(
            modifier = Modifier,
            titleCardText = stringResource(R.string.city_attractions_card_title),
            subTitleCardText = stringResource(R.string.city_attractions_card_subtitle, city.name),
            image = R.drawable.top_attractions,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        LongInfoCard(
            modifier = Modifier,
            titleCardText = stringResource(R.string.city_history_card_title),
            subTitleCardText = stringResource(R.string.city_history_card_subtitle),
            image = R.drawable.ic_scroll_outline,
            onClick = {}
        )
    }
}