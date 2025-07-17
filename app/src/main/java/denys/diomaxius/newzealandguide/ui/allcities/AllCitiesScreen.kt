package denys.diomaxius.newzealandguide.ui.allcities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.ui.common.UiState

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
            Text(text = it.name)
        }
    }
}