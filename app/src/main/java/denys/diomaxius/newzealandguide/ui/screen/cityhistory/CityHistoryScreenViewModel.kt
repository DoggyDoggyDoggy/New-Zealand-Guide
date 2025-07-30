package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityHistoryByIdUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityHistoryScreenViewModel @Inject constructor(
    private val getCityHistoryByIdUseCase: GetCityHistoryByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _cityHistoryUiState = MutableStateFlow<UiState<CityHistory>>(UiState.Loading)
    val cityHistoryUiState = _cityHistoryUiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        getCityHistory()
    }

    private fun getCityHistory() = viewModelScope.launch {
        _cityHistoryUiState.value = try {
            UiState.Success(getCityHistoryByIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}