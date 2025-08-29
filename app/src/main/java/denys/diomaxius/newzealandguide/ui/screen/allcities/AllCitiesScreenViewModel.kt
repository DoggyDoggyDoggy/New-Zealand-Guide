package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.AddFavoriteCityIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetAllCitiesUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.RemoveFavoriteCityIdUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import denys.diomaxius.newzealandguide.ui.mapper.toUi
import denys.diomaxius.newzealandguide.ui.model.CityUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val addFavoriteCityIdUseCase: AddFavoriteCityIdUseCase,
    private val removeFavoriteCityIdUseCase: RemoveFavoriteCityIdUseCase,
) : ViewModel() {
    private val _citiesUiState = MutableStateFlow<UiState<List<CityUi>>>(UiState.Loading)
    val citiesUiState = _citiesUiState.asStateFlow()

    init {
        observeCities()
    }

    fun toggleFavorite(id: String, currentlyFavorite: Boolean) {
        if (currentlyFavorite) removeFavoriteCity(id) else addFavoriteCity(id)
    }

    private fun observeCities() {
        viewModelScope.launch {
            getAllCitiesUseCase()
                .map { list -> list.map { it.city.toUi(it.isFavorite) } }
                .onStart { _citiesUiState.value = UiState.Loading }
                .catch { e -> _citiesUiState.value = UiState.Error(e) }
                .collect { uiList -> _citiesUiState.value = UiState.Success(uiList) }
        }
    }

    private fun addFavoriteCity(id: String) = viewModelScope.launch {
        addFavoriteCityIdUseCase(id)
    }

    private fun removeFavoriteCity(id: String) = viewModelScope.launch {
        removeFavoriteCityIdUseCase(id)
    }
}