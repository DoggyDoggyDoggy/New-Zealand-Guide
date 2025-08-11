package denys.diomaxius.newzealandguide.ui.common.components.infocard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LongInfoCard(
    modifier: Modifier = Modifier,
    titleCardText: String,
    subTitleCardText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleCardText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = subTitleCardText,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LongInfoCardPreview() {
    LongInfoCard(
        modifier = Modifier,
        titleCardText = "Top Cities & Towns",
        subTitleCardText = "Explore New Zealand",
        onClick = {}
    )
}