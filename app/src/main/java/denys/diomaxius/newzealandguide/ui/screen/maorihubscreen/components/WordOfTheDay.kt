package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.R

@Composable
fun WordOfTheDay(
    modifier: Modifier,
    wordOfTheDay: Pair<String, String>
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(12.dp),
                shadow = Shadow(
                    radius = 3.dp,
                    spread = 0.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    offset = DpOffset(x = -1.dp, -1.dp)
                )
            )
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceBright
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.outline_chat_bubble_24),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    modifier = Modifier,
                    text = "Word of the day",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
            Text(
                text = wordOfTheDay.first,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Text(
                text = wordOfTheDay.second,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}