package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeData = viewModel.homeData

    Text(
        text = homeData.photos.first(),
        modifier = Modifier
    )
}