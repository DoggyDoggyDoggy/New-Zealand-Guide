package denys.diomaxius.newzealandguide.ui.screen.favoritecities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.GetFavoriteCitiesUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.RemoveFavoriteCityIdUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCitiesViewModelScreen @Inject constructor(
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
    private val removeFavoriteCityIdUseCase: RemoveFavoriteCityIdUseCase,
) : ViewModel() {

    private val _favoriteCitiesUiState = MutableStateFlow<UiState<List<City>>>(UiState.Loading)
    val favoriteCitiesUiState = _favoriteCitiesUiState.asStateFlow()

    init {
        loadFavoriteCities()
    }

    fun loadFavoriteCities() = viewModelScope.launch {
        getFavoriteCitiesUseCase()
            .onStart { _favoriteCitiesUiState.value = UiState.Loading }
            .catch { e -> _favoriteCitiesUiState.value = UiState.Error(e) }
            .collectLatest { cities ->
                _favoriteCitiesUiState.value = UiState.Success(cities)
            }
    }
}