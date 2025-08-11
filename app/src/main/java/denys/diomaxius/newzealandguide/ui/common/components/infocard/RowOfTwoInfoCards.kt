package denys.diomaxius.newzealandguide.ui.common.components.infocard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TwoInfoCardsRow(
    modifier: Modifier = Modifier,
    firstCardText: String,
    secondCardText: String,
    firstCardOnClick: () -> Unit,
    secondCardOnClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        InfoCardSquare(
            modifier = Modifier.weight(1f),
            cardText = firstCardText,
            onClick = firstCardOnClick
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        InfoCardSquare(
            modifier = Modifier.weight(1f),
            cardText = secondCardText,
            onClick = secondCardOnClick
        )
    }
}