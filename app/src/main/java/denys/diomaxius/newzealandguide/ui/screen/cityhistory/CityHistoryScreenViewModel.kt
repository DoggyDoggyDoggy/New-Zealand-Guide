package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityHistoryByCityIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityHistoryScreenViewModel @Inject constructor(
    private val getCityHistoryByCityIdUseCase: GetCityHistoryByCityIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _cityHistory = MutableStateFlow<CityHistory>(
        CityHistory(
            cityId = "",
            paragraphs = emptyList()
        )
    )
    val cityHistory = _cityHistory.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        getCityHistory()
    }

    private fun getCityHistory() = viewModelScope.launch {
        _cityHistory.value = getCityHistoryByCityIdUseCase(cityId)
    }
}