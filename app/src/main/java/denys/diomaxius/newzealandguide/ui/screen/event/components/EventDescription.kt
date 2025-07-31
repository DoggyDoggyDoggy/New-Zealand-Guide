package denys.diomaxius.newzealandguide.ui.screen.event.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventDescription(
    eventDescription: String
) {
    Text(
        text = "Description:",
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = eventDescription,
        fontSize = 16.sp
    )
}