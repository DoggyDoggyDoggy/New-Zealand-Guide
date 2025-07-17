package denys.diomaxius.newzealandguide.ui.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.GetCityByIdUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityScreenViewModel @Inject constructor(
    private val getCityByIdUseCase: GetCityByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _city = MutableStateFlow<UiState<City>>(UiState.Loading)
    val city: StateFlow<UiState<City>> = _city.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadCity()
    }

    private fun loadCity() = viewModelScope.launch {
        _city.value = try {
            UiState.Success(getCityByIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}