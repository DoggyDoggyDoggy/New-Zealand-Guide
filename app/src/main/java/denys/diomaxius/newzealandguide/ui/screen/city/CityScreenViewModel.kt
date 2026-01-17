package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityByIdUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityScreenViewModel @Inject constructor(
    private val getCityByIdUseCase: GetCityByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<City>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadCity()

    }

    private fun loadCity() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(getCityByIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}