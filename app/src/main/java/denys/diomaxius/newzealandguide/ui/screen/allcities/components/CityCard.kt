package denys.diomaxius.newzealandguide.ui.screen.allcities.components

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
import denys.diomaxius.newzealandguide.ui.components.TextOverlay

@Composable
fun CityCard(
    modifier: Modifier = Modifier,
    city: City,
    navigateToCity: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .clickable {
                navigateToCity()
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = "file:///android_asset/" + city.photos.first(),
                contentDescription = city.name,
                contentScale = ContentScale.FillWidth
            )

            TextOverlay(text = city.name)
        }
    }
}