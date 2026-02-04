package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.GetAllCitiesFlowUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    getAllCitiesFlowUseCase: GetAllCitiesFlowUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {
    private val _favoriteFilter = MutableStateFlow(false)
    val favoriteFilter = _favoriteFilter.asStateFlow()

    val allCities = getAllCitiesFlowUseCase(onlyFavorites = false)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val favoriteCities = getAllCitiesFlowUseCase(onlyFavorites = true)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleFavorite(cityId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(cityId)
        }
    }

    fun toggleFavoriteFilter() {
        _favoriteFilter.value = !_favoriteFilter.value
    }
}