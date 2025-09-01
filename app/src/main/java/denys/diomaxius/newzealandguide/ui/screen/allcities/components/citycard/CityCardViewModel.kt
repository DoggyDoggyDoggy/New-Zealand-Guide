package denys.diomaxius.newzealandguide.ui.screen.allcities.components.citycard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.AddFavoriteCityIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.RemoveFavoriteCityIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityCardViewModel @Inject constructor(
    private val addFavoriteCityIdUseCase: AddFavoriteCityIdUseCase,
    private val removeFavoriteCityIdUseCase: RemoveFavoriteCityIdUseCase,
) : ViewModel() {
    fun toggleFavorite(id: String, currentlyFavorite: Boolean) {
        if (currentlyFavorite) removeFavoriteCity(id) else addFavoriteCity(id)
    }

    private fun addFavoriteCity(id: String) = viewModelScope.launch {
        addFavoriteCityIdUseCase(id)
    }

    private fun removeFavoriteCity(id: String) = viewModelScope.launch {
        removeFavoriteCityIdUseCase(id)
    }
}