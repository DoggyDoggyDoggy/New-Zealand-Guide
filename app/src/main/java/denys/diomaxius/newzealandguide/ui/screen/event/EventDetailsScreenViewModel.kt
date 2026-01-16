package denys.diomaxius.newzealandguide.ui.screen.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsScreenViewModel @Inject constructor(
    private val getCityEventUseCase: GetCityEventUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<CityEvent>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val eventId: String = checkNotNull(savedStateHandle["eventId"])
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadEvent()
    }

    private fun loadEvent() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(getCityEventUseCase(cityId = cityId, eventId = eventId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}