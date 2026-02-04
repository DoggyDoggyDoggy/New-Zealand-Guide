package denys.diomaxius.newzealandguide.ui.screen.event.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

@Composable
fun EventBottomBar(
    modifier: Modifier,
    event: CityEvent,
    context: Context,
) {
    Box(
        modifier = modifier
    ) {
        BuyTicketButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(vertical = 8.dp)
                .height(50.dp),
            event = event,
            context = context
        )
    }
}

@Composable
fun BuyTicketButton(
    modifier: Modifier = Modifier,
    event: CityEvent,
    context: Context,
) {
    Button(
        modifier = modifier,
        onClick = {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, event.url.toUri())
            )
        }
    ) {
        Text(
            text = stringResource(R.string.event_buy_button),
            fontSize = 20.sp
        )
    }
}