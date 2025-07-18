package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.ui.common.components.UiStateHandler

@Composable
fun CityPlacesScreen(
    viewModel: CityPlacesScreenViewModel = hiltViewModel()
) {
    val cityPlaces by viewModel.places.collectAsState()

    UiStateHandler(cityPlaces) {
        Content(it)
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