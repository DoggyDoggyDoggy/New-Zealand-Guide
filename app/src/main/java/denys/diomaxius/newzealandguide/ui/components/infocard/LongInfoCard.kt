package denys.diomaxius.newzealandguide.ui.components.infocard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.R

@Composable
fun LongInfoCard(
    modifier: Modifier = Modifier,
    titleCardText: String,
    subTitleCardText: String,
    onClick: () -> Unit,
    image: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = image),
                contentDescription = "Leaf"
            )

            Spacer(modifier= Modifier.width(12.dp))

            Column(
                modifier = Modifier
            ) {
                Text(
                    text = titleCardText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = subTitleCardText,
                    fontSize = 16.sp
                )
            }
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
        onClick = {},
        image = R.drawable.ic_building_outline
    )
}