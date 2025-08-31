package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.GetAllCitiesUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import denys.diomaxius.newzealandguide.ui.mapper.toUi
import denys.diomaxius.newzealandguide.ui.model.CityUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase
) : ViewModel() {
    private val _showFavorite = MutableStateFlow<Boolean>(false)
    val showFavorite = _showFavorite.asStateFlow()

    private val _citiesUiState = MutableStateFlow<UiState<List<CityUi>>>(UiState.Loading)
    val citiesUiState = _citiesUiState.asStateFlow()

    init {
        observeCities()
    }

    fun toggleFavorite() {
        _showFavorite.value = !_showFavorite.value
    }

    private fun observeCities() {
        viewModelScope.launch {
            combine(
                getAllCitiesUseCase(),
                _showFavorite
            ) { cities, showFavorite ->
                if (showFavorite) {
                    cities.filter { it.isFavorite }
                } else {
                    cities
                }
            }
                .map { list -> list.map { it.city.toUi(it.isFavorite) } }
                .onStart { _citiesUiState.value = UiState.Loading }
                .catch { e -> _citiesUiState.value = UiState.Error(e) }
                .collect { uiList -> _citiesUiState.value = UiState.Success(uiList) }
        }
    }
}