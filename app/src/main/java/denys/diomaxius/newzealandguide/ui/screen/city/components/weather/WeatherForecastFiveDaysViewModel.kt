package denys.diomaxius.newzealandguide.ui.screen.city.components.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityWeatherByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastFiveDaysViewModel @Inject constructor(
    private val getCityWeatherByCityIdUseCase: GetCityWeatherByCityIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<CityWeather>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadCityWeather()
    }

    private fun loadCityWeather() = viewModelScope.launch {
        _uiState.value = try {
            UiState.Success(
                getCityWeatherByCityIdUseCase(cityId)
            )
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}