package denys.diomaxius.newzealandguide.ui.components.cityphotoslider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun CityPhotoSlider(
    modifier: Modifier = Modifier,
    photos: List<String>
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        AutoScrollPager(
            items = photos,
            modifier = Modifier.fillMaxWidth()
        ) { url ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = url,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}