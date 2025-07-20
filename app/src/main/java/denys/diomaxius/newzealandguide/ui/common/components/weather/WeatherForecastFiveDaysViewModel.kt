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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastFiveDaysViewModel @Inject constructor(
    private val getWeatherByCityIdUseCase: GetWeatherByCityIdUseCase,
    private val getWeatherIconUseCase: GetWeatherIconUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<WeatherUiModel>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        loadForecastsWithIcons()
    }

    // Map unique icons to reduces the number of network calls
    // and improves the performance of loading the weather forecast
    private fun loadForecastsWithIcons() = viewModelScope.launch {
        _uiState.value = try {
            val raw = getWeatherByCityIdUseCase(cityId)

            val iconIds = raw.map { it.icon }.distinct()

            val iconsMap = coroutineScope {
                iconIds
                    .map { id -> async { id to getWeatherIconUseCase(id) } }
                    .awaitAll()
                    .toMap()
            }

            val enriched = raw.map { w ->
                WeatherUiModel(w.dateTime, w.temperature, iconsMap[w.icon].orEmpty())
            }

            UiState.Success(enriched)
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}