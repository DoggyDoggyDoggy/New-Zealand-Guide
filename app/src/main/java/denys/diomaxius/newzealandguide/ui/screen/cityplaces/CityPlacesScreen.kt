package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPlacesScreen(
    viewModel: CityPlacesScreenViewModel = hiltViewModel(),
    cityName: String,
) {
    val uiState by viewModel.uiState.collectAsState()

    val navHostController = LocalNavController.current

    //Change to UiState later on
    UiStateHandler(
        state = uiState,
        loading = { ScreenLoading() }
    ) { cityPlaces ->
        Scaffold(
            topBar = {
                TopBar(
                    text = stringResource(R.string.top_bar_attraction_in_city, cityName),
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.navigateUp()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                cityPlacesTopics = cityPlaces
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    cityPlacesTopics: List<CityPlace>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        items(cityPlacesTopics) { topic ->
            CityPlace(topic)
        }
    }
}

@Composable
fun CityPlace(topic: CityPlace) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = topic.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            if (topic.image != "") {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = "file:///android_asset/${topic.image}",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Spacer(
                    modifier = Modifier.height(5.dp)
                )
            }

            Text(
                text = topic.description,
                fontSize = 16.sp
            )
        }
    }
}