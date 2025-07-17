package denys.diomaxius.newzealandguide.ui.cityhistory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.usecase.GetCityHistoryByIdUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityHistoryScreenViewModel @Inject constructor(
    private val getCityHistoryByIdUseCase: GetCityHistoryByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _cityHistory = MutableStateFlow<UiState<CityHistory>>(UiState.Loading)
    val cityHistory = _cityHistory.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        getCityHistory()
    }

    private fun getCityHistory() = viewModelScope.launch {
        try {
            _cityHistory.value = UiState.Success(getCityHistoryByIdUseCase(cityId))
        } catch (e: Exception) {
            _cityHistory.value = UiState.Error(e)
        }
    }
}