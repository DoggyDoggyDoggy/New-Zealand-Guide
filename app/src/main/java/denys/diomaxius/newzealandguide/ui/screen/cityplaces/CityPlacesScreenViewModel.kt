package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.usecase.city.GetPlacesForCityByIdUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityPlacesScreenViewModel @Inject constructor(
    private val getPlacesForCityByIdUseCase: GetPlacesForCityByIdUseCase,
    private val incrementReviewActionUseCase: IncrementReviewActionUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<CityPlace>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadPlaces()
        increaseReviewAction()
    }

    private fun loadPlaces() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(getPlacesForCityByIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }

    private fun increaseReviewAction() = viewModelScope.launch {
        incrementReviewActionUseCase()
    }
}