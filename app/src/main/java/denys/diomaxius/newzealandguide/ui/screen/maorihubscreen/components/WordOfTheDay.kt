package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.R

@Composable
fun WordOfTheDay() {
    Card (
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(6.dp),
    ){
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.outline_chat_bubble_24),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.tertiary
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    modifier = Modifier,
                    text = "Word of the day",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
            Text(
                text = "Maori",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Text(
                text = "English",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }
    }
}