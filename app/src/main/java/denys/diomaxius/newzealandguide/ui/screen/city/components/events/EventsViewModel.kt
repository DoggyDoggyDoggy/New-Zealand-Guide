package denys.diomaxius.newzealandguide.ui.screen.city.components.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventsByIdUseCase
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    getCityEventsIdUseCase: GetCityEventsByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    val events = getCityEventsIdUseCase(20, cityId).cachedIn(viewModelScope)
}