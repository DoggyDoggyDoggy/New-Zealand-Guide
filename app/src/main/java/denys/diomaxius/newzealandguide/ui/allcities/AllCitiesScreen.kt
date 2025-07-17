package denys.diomaxius.newzealandguide.ui.allcities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AllCitiesScreen(
    viewModel: AllCitiesScreenViewModel = hiltViewModel()
) {
    val cities by viewModel.cities.collectAsState()

    LazyColumn {
        items(cities) {
            Text(text = it.name)
        }
    }
}