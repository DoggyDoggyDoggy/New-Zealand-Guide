package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCitiesPagerFlowUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.ToggleFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    getCitiesPagerFlowUseCase: GetCitiesPagerFlowUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {
    val lazyCities = getCitiesPagerFlowUseCase(5).cachedIn(viewModelScope)

    fun toggleFavorite(cityId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(cityId)
        }
    }
}