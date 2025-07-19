package denys.diomaxius.newzealandguide.ui.common.components.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import denys.diomaxius.newzealandguide.domain.usecase.GetWeatherByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastFiveDaysViewModel @Inject constructor(
    private val getWeatherByCityIdUseCase: GetWeatherByCityIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _weatherForecastUiState = MutableStateFlow<UiState<List<Weather>>>(UiState.Loading)
    val weatherForecastUiState = _weatherForecastUiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadWeatherForecast()
    }

    private fun loadWeatherForecast() = viewModelScope.launch {
        _weatherForecastUiState.value = try {
            UiState.Success(getWeatherByCityIdUseCase(cityId))
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}