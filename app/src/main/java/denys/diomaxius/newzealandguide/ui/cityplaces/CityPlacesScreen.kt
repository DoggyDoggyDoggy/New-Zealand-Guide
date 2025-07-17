package denys.diomaxius.newzealandguide.ui.cityplaces

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.ui.common.UiState

@Composable
fun CityPlacesScreen(
    viewModel: CityPlacesScreenViewModel = hiltViewModel()
) {
    val cityPlaces by viewModel.places.collectAsState()

    when(cityPlaces) {
        is UiState.Loading -> {
            Text(text = "Loading")
        }
        is UiState.Success -> Content((cityPlaces as UiState.Success<List<CityPlaceTopic>>).data)
        is UiState.Error -> {
            Text(text = "Error: ${(cityPlaces as UiState.Error).error}")
        }
    }
}

@Composable
fun Content(data: List<CityPlaceTopic>) {
    LazyColumn {
        items(data) {
            Text(it.title)
        }
    }
}