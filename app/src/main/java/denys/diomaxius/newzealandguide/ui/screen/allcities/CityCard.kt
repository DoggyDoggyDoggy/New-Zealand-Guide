package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.ui.common.components.TextOverlay
import denys.diomaxius.newzealandguide.ui.model.CityUi

@Composable
fun CityCard(
    viewModel: CityCardViewModel = hiltViewModel(),
    city: CityUi,
    navigateToCity: () -> Unit
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


            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
                    .size(58.dp)
                    .clickable{
                        viewModel.toggleFavorite(city.id, city.isFavorite)
                    },
                imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White
            )

            TextOverlay(text = city.name)
        }
    }
}

@Preview
@Composable
fun CityCardPreview() {
    CityCard(
        city = CityUi(
            id = "2",
            name = "Auckland",
            photos = listOf("https://res.cloudinary.com/dpeak0qy7/image/upload/v1754316593/New_Project_3_lgsztg.png"),
            isFavorite = false
        ),
        navigateToCity = {}
    )
}