package denys.diomaxius.newzealandguide.ui.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import denys.diomaxius.newzealandguide.ui.common.UiState

@Composable
fun <T> UiStateHandler(
    state: UiState<T>,
    loading: @Composable () -> Unit = { Text("Loading…") },
    error: @Composable (throwable: Throwable?) -> Unit = { Text("Something went wrong") },
    content: @Composable (data: T) -> Unit
) {
    when (state) {
        is UiState.Loading -> loading()
        is UiState.Error   -> error(state.error)
        is UiState.Success -> content(state.data)
    }
}