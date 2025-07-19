package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.GetAllCitiesUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
) : ViewModel() {
    private val _citiesUiState = MutableStateFlow<UiState<List<City>>>(UiState.Loading)
    val citiesUiState = _citiesUiState.asStateFlow()

    init {
        loadCities()
    }

    private fun loadCities() = viewModelScope.launch {
        _citiesUiState.value = try {
            UiState.Success(getAllCitiesUseCase())
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }

}