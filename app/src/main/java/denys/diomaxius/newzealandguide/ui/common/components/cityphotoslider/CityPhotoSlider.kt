package denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.City

@Composable
fun CityPhotoSlider(
    modifier: Modifier = Modifier,
    city: City
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        AutoScrollPager(
            items = city.photos,
            modifier = Modifier
                .fillMaxWidth()
        ) { url ->
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = url,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}