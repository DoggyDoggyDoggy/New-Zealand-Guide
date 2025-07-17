package denys.diomaxius.newzealandguide.ui.allcities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.ui.common.UiState
import denys.diomaxius.newzealandguide.ui.common.components.TextOverlay

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel()
) {
    val cities by viewModel.cities.collectAsState()

    when(cities) {
        is UiState.Loading -> {
            Text(text = "Loading")
        }
        is UiState.Success -> Content((cities as UiState.Success<List<City>>).data)
        is UiState.Error -> {
            Text(text = "Error")
        }
    }
}

@Composable
fun Content(cities: List<City>) {
    LazyColumn {
        items(cities) {
            CityCard(it)
        }
    }
}

@Composable
fun CityCard(
    city: City
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
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