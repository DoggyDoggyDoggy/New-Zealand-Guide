package denys.diomaxius.newzealandguide.ui.screen.favoritecities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler

@Composable
fun FavoriteCitiesScreen(
    viewModel: FavoriteCitiesViewModelScreen = hiltViewModel()
) {
    val favoriteCitiesUiState by viewModel.favoriteCitiesUiState.collectAsState()

    UiStateHandler(
        state = favoriteCitiesUiState,
        loading = { ScreenLoading() }
    ) {
        LazyColumn {
            items(it) {
                Text(text = it.name)
            }
        }
    }
}