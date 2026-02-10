package denys.diomaxius.playground.maoriwords

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FactCard(fact: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Column (
            modifier = Modifier.padding(12.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = ""
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    modifier = Modifier,
                    text = "Quick tip",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
            Text(
                text = fact
            )
        }
    }
}