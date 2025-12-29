package denys.diomaxius.newzealandguide.ui.components.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventsByIdUseCase
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    getCityEventsIdUseCase: GetCityEventsByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    val events = getCityEventsIdUseCase(5, cityId).cachedIn(viewModelScope)
}