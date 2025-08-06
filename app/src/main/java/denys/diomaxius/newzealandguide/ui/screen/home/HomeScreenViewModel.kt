package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.home.Home
import denys.diomaxius.newzealandguide.domain.usecase.home.GetHomeDataUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<UiState<Home>>(UiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadHome()
    }

    private fun loadHome() = viewModelScope.launch {
        _homeUiState.value = try {
            UiState.Success(getHomeDataUseCase())
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}