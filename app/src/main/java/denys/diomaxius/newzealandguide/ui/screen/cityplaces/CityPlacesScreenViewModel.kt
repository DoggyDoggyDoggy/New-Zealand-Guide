package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.usecase.city.GetPlacesForCityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityPlacesScreenViewModel @Inject constructor(
    private val getPlacesForCityByIdUseCase: GetPlacesForCityByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _cityPlaces = MutableStateFlow<List<CityPlace>>(emptyList())
    val cityPlaces = _cityPlaces.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadPlaces()
    }

    private fun loadPlaces() = viewModelScope.launch {
        _cityPlaces.value = getPlacesForCityByIdUseCase(cityId)
    }
}