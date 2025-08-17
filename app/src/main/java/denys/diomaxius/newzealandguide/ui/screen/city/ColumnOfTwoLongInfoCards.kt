package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.ui.common.components.infocard.LongInfoCard

@Composable
fun ColumnOfTwoLongInfoCards(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LongInfoCard(
            modifier = modifier,
            titleCardText = "Top Cities & Towns",
            subTitleCardText = "Explore New Zealand",
            image =  R.drawable.ic_scroll_outline,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        LongInfoCard(
            modifier = modifier,
            titleCardText = "Top Cities & Towns",
            subTitleCardText = "Explore New Zealand",
            image =  R.drawable.ic_scroll_outline,
            onClick = {}
        )
    }
}