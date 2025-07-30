package denys.diomaxius.newzealandguide.ui.common.components.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.domain.usecase.GetEventsInCityUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsRowViewModel @Inject constructor(
    private val getEventsInCityUseCase: GetEventsInCityUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<Event>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadEvents()
    }

    private fun loadEvents() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(getEventsInCityUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}