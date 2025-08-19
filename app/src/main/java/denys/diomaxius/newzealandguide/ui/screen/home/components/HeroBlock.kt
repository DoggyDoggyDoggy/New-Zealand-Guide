package denys.diomaxius.newzealandguide.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.R
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
                .padding(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.home_hero_block_title),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                text = stringResource(R.string.home_hero_block_subtitle),
                textAlign = TextAlign.Center,
                fontSize = 26.sp
            )
        }

        CityPhotoSlider(
            photos = photos
        )
    }
}