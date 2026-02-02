package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityByIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventsByIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityWeatherByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.domain.repository.ConnectivityObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class CityScreenViewModel @Inject constructor(
    private val getCityWeatherByCityIdUseCase: GetCityWeatherByCityIdUseCase,
    private val getCityByIdUseCase: GetCityByIdUseCase,
    getCityEventsIdUseCase: GetCityEventsByIdUseCase,
    connectivityObserver: ConnectivityObserver,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    data class CityScreenUiState(
        val city: UiState<City> = UiState.Loading,
        val weather: UiState<List<CityWeather>> = UiState.Loading,
    )

    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    private val _uiState = MutableStateFlow(CityScreenUiState())
    val uiState: StateFlow<CityScreenUiState> = _uiState.asStateFlow()

    val events = getCityEventsIdUseCase(20, cityId).cachedIn(viewModelScope)

    val hasInternetConnection: StateFlow<Boolean> =
        connectivityObserver.hasInternetConnection
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    init {
        loadAll()
    }

    private fun loadAll() {
        viewModelScope.launch {
            supervisorScope {
                launch { loadCity() }
                launch { loadWeather() }
            }
        }
    }

    private suspend fun loadCity() {
        _uiState.update { it.copy(city = UiState.Loading) }
        try {
            val city = getCityByIdUseCase(cityId)
            _uiState.update { it.copy(city = UiState.Success(city)) }
        } catch (e: Exception) {
            _uiState.update { it.copy(city = UiState.Error(e)) }
        }
    }

    private suspend fun loadWeather() {
        _uiState.update { it.copy(weather = UiState.Loading) }
        try {
            val weather = getCityWeatherByCityIdUseCase(cityId)
            _uiState.update { it.copy(weather = UiState.Success(weather)) }
        } catch (e: Exception) {
            _uiState.update { it.copy(weather = UiState.Error(e)) }
        }
    }
}