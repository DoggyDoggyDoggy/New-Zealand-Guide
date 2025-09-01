package denys.diomaxius.newzealandguide.ui.screen.allcities.components.citycard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.ui.common.components.TextOverlay
import denys.diomaxius.newzealandguide.ui.model.CityUi
import kotlinx.coroutines.launch

@Composable
fun CityCard(
    viewModel: CityCardViewModel = hiltViewModel(),
    city: CityUi,
    navigateToCity: () -> Unit
) {
    Card(
        modifier = Modifier
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
                model = city.photos.first(),
                contentDescription = city.name,
                contentScale = ContentScale.FillWidth
            )

            val interactionSource = remember { MutableInteractionSource() }
            val scale = remember { Animatable(1f) }
            val scope = rememberCoroutineScope()

            Icon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
                    .size(58.dp)
                    .scale(scale.value)
                    .clickable (
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        viewModel.toggleFavorite(city.id, city.isFavorite)
                        if (!city.isFavorite) {
                            scope.launch {
                                scale.animateTo(1.2f, animationSpec = tween(delayMillis = 120))
                                scale.animateTo(1f, animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                ))
                            }
                        }
                    },
                imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color(0xFFF87272)
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