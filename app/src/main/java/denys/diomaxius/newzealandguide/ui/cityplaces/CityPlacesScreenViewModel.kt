package denys.diomaxius.newzealandguide.ui.cityplaces

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.domain.usecase.GetPlacesByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityPlacesViewModel @Inject constructor(
    private val getPlacesByCityIdUseCase: GetPlacesByCityIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _places = MutableStateFlow<UiState<List<CityPlaceTopic>>>(UiState.Loading)
    val places: StateFlow<UiState<List<CityPlaceTopic>>> = _places

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init { loadPlaces() }

    private fun loadPlaces() = viewModelScope.launch {
        _places.value = try {
            UiState.Success(getPlacesByCityIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}
