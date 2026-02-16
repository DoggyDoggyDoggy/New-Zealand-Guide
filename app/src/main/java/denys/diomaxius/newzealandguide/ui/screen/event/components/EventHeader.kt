package denys.diomaxius.newzealandguide.ui.screen.event.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun EventHeader(
    name: String,
    image: String
) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        lineHeight = 28.sp
    )

    Spacer(
        modifier = Modifier.height(4.dp)
    )

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        model = image,
        contentScale = ContentScale.FillWidth,
        contentDescription = "Image"
    )
}