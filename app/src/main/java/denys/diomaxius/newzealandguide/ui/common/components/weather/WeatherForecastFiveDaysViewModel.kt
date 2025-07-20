package denys.diomaxius.newzealandguide.ui.common.components.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.GetWeatherByCityIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.GetWeatherIconUseCase
import denys.diomaxius.newzealandguide.ui.common.UiState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class WeatherUiModel(
    val dateTime: LocalDateTime,
    val temperature: Double,
    val iconUrl: String
)

@HiltViewModel
class WeatherForecastFiveDaysViewModel @Inject constructor(
    private val getWeatherByCityIdUseCase: GetWeatherByCityIdUseCase,
    private val getWeatherIconUseCase: GetWeatherIconUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<WeatherUiModel>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadForecastsWithIcons()
    }

    private fun loadForecastsWithIcons() = viewModelScope.launch {
        _uiState.value = try {
            val raw = getWeatherByCityIdUseCase(cityId)

            val enriched = raw.map { w ->
                async {
                    WeatherUiModel(
                        dateTime = w.dateTime,
                        temperature = w.temperature,
                        iconUrl = getWeatherIconUseCase(w.icon)
                    )
                }
            }.awaitAll()

            UiState.Success(enriched)
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}