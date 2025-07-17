package denys.diomaxius.newzealandguide.ui.city

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.UiState

@Composable
fun CityScreen(
    viewModel: CityScreenViewModel = hiltViewModel()
) {
    val city by viewModel.city.collectAsState()
    val navHostController = LocalNavController.current

    when(city) {
        is UiState.Loading -> {
            Text(text = "Loading")
        }
        is UiState.Success -> Content(
          city = (city as UiState.Success<City>).data
        )
        is UiState.Error -> {
            Text(text = "Error")
        }
    }
}

@Composable
fun Content(city: City) {
    Text(text = city.name)
}