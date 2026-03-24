package denys.diomaxius.newzealandguide.ui.screen.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.ToggleEventFavoriteUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsScreenViewModel @Inject constructor(
    getCityEventUseCase: GetCityEventUseCase,
    private val toggleEventFavoriteUseCase: ToggleEventFavoriteUseCase,
    private val incrementReviewActionUseCase: IncrementReviewActionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val eventId: String = checkNotNull(savedStateHandle["eventId"])
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    val uiState: StateFlow<UiState<CityEvent>> = getCityEventUseCase(cityId, eventId)
        .map<CityEvent, UiState<CityEvent>> { event ->
            UiState.Success(event)
        }
        .catch { e ->
            emit(UiState.Error(e))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )

    init {
        increaseReviewAction()
    }

    fun toggleFavorite() = viewModelScope.launch {
        incrementReviewActionUseCase()
        toggleEventFavoriteUseCase(cityId, eventId)
    }

    private fun increaseReviewAction() = viewModelScope.launch {
        incrementReviewActionUseCase()
    }
}