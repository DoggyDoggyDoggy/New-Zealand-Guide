package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.ui.common.components.TextOverlay

@Composable
fun CityCard(
    city: City,
    navigateToCity: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable {
                navigateToCity()
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = city.photos.first(),
                contentDescription = city.name,
                contentScale = ContentScale.FillWidth
            )

            TextOverlay(text = city.name)
        }
    }
}