package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun CityPlacesScreen(
    viewModel: CityPlacesScreenViewModel = hiltViewModel(),
) {
    val cityPlacesUiState by viewModel.placesUiState.collectAsState()
    val navHostController = LocalNavController.current

    UiStateHandler(cityPlacesUiState) { cityPlacesTopics ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "Top Things to Do in cityName",
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.popBackStack()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                cityPlacesTopics = cityPlacesTopics
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cityPlacesTopics: List<CityPlaceTopic>,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(cityPlacesTopics) { topic ->
            CityPlace(topic)
        }
    }
}

@Composable
fun CityPlace(topic: CityPlaceTopic) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = topic.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            if (topic.image != "") {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                    model = topic.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Spacer(
                    modifier = Modifier.height(5.dp)
                )
            }

            Text(
                text = topic.paragraph,
                fontSize = 16.sp
            )
        }
    }
}