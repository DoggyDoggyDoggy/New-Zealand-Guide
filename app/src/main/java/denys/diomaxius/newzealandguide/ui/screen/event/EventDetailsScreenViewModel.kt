package denys.diomaxius.newzealandguide.ui.screen.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.domain.usecase.event.GetEventInCityByIdUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsScreenViewModel @Inject constructor(
    private val getEventInCityByIdUseCase: GetEventInCityByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<Event>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val eventId: String = checkNotNull(savedStateHandle["eventId"])
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadEvent()
    }

    private fun loadEvent() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(getEventInCityByIdUseCase(cityId = cityId, eventId = eventId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}