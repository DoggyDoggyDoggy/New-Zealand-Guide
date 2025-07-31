package denys.diomaxius.newzealandguide.ui.screen.event.components

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import denys.diomaxius.newzealandguide.domain.model.event.Event

@Composable
fun BuyTicketButton(
    modifier: Modifier = Modifier,
    event: Event,
    context: Context
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
            text = "Buy Tickets",
            fontSize = 20.sp
        )
    }
}