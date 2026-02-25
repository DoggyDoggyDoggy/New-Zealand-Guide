package denys.diomaxius.newzealandguide.ui.screen.maorilearningresources

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

@Composable
fun ResourceCard(
    modifier: Modifier = Modifier,
    context: Context
) {
    Card(
        modifier = modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp)
                    .padding(vertical = 16.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
                thickness = 10.dp,
                color = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 24.dp)
            ) {
                Text(
                    text = "Māori Language Learning",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Free online lessons to help you learn basic te reo Māori, including greetings, pronunciation, and everyday phrases.",
                    fontSize = 16.sp,
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(1f),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp
                    ),
                    onClick = {
                        openCustomTab(context, "https://maoridictionary.co.nz/word/845")
                    },
                ) {
                    Text(
                        text = "Learn More",
                        fontSize = 16.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}

fun openCustomTab(context: Context, url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, url.toUri())
}