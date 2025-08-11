package denys.diomaxius.newzealandguide.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider.CityPhotoSlider

@Composable
fun HeroBlock(
    modifier: Modifier = Modifier,
    photos: List<String>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Kia ora!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                text = "Explore New Zealand",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }

        CityPhotoSlider(
            photos = photos
        )
    }
}