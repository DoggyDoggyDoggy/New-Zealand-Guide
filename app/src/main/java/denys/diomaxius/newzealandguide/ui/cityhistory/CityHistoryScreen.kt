package denys.diomaxius.newzealandguide.ui.cityhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.ui.common.UiState

@Composable
fun CityHistoryScreen(
    viewModel: CityHistoryScreenViewModel = hiltViewModel(),
) {
    val cityHistory by viewModel.cityHistory.collectAsState()

    when (cityHistory) {
        is UiState.Loading -> {
            Text(text = "Loading")
        }
        is UiState.Success -> Content(
            cityHistory = (cityHistory as UiState.Success<CityHistory>).data
        )
        is UiState.Error -> {
            Text(text = "Error")
        }
    }

}

@Composable
fun Content(
    cityHistory: CityHistory,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        cityHistory.paragraphs.forEach {
            Text(
                text = it,
                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
    }
}