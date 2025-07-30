package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.domain.usecase.city.GetPlacesByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityPlacesScreenViewModel @Inject constructor(
    private val getPlacesByCityIdUseCase: GetPlacesByCityIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _placesUiState = MutableStateFlow<UiState<List<CityPlaceTopic>>>(UiState.Loading)
    val placesUiState = _placesUiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadPlaces()
    }

    private fun loadPlaces() = viewModelScope.launch {
        _placesUiState.value = try {
            UiState.Success(getPlacesByCityIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}