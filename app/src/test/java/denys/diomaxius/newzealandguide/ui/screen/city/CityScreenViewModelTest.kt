package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.model.city.WeatherResult
import denys.diomaxius.newzealandguide.domain.repository.ConnectivityObserver
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityByIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventsByIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityWeatherByCityIdUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.GetFavoriteCityEventsFlowUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.util.MainDispatcherExtension
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherExtension::class)
class CityScreenViewModelTest {

    private val getCityWeatherUseCase = mockk<GetCityWeatherByCityIdUseCase>()
    private val getCityByIdUseCase = mockk<GetCityByIdUseCase>()
    private val getCityEventsUseCase = mockk<GetCityEventsByIdUseCase>()
    private val getFavoriteEventsUseCase = mockk<GetFavoriteCityEventsFlowUseCase>()
    private val connectivityObserver = mockk<ConnectivityObserver>()

    private val cityId = "wellington_666"
    private val savedStateHandle = SavedStateHandle(mapOf("cityId" to cityId))

    private lateinit var viewModel: CityScreenViewModel

    @BeforeEach
    fun setup() {
        every { connectivityObserver.hasInternetConnection } returns flowOf(true)
        every { getFavoriteEventsUseCase(cityId) } returns flowOf(emptyList())
        every { getCityEventsUseCase(any(), cityId) } returns flowOf(PagingData.empty())
    }

    @Test
    fun `loadAll should transition both city and weather to Success`() = runTest {
        val mockCity = mockk<City>()
        val mockWeather = listOf(mockk<CityWeather>())

        coEvery { getCityByIdUseCase(cityId) } coAnswers {
            delay(10)
            mockCity
        }
        coEvery { getCityWeatherUseCase(cityId) } coAnswers {
            delay(20)
            WeatherResult.Success(mockWeather)
        }

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        viewModel.uiState.test {
            val initialState = awaitItem()
            assertThat(initialState.city).isInstanceOf(UiState.Loading::class.java)
            assertThat(initialState.weather).isInstanceOf(UiState.Loading::class.java)

            val stateAfterCity = awaitItem()
            assertThat(stateAfterCity.city).isInstanceOf(UiState.Success::class.java)
            assertThat(stateAfterCity.weather).isInstanceOf(UiState.Loading::class.java)

            val finalState = awaitItem()
            assertThat(finalState.weather).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    fun `when weather fails city should still be success`() = runTest {
        val mockCity = mockk<City>()
        val exception = Exception("API Down")

        coEvery { getCityByIdUseCase(cityId) } coAnswers {
            delay(10)
            mockCity
        }
        coEvery { getCityWeatherUseCase(cityId) } coAnswers {
            delay(20)
            WeatherResult.Error(exception)
        }

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        viewModel.uiState.test {
            assertThat(awaitItem().city).isInstanceOf(UiState.Loading::class.java)

            val stateAfterCity = awaitItem()
            assertThat(stateAfterCity.city).isInstanceOf(UiState.Success::class.java)
            assertThat(stateAfterCity.weather).isInstanceOf(UiState.Loading::class.java)

            val finalState = awaitItem()
            assertThat(finalState.weather).isInstanceOf(UiState.Error::class.java)
            assertThat((finalState.weather as UiState.Error).error).isEqualTo(exception)
        }
    }

    @Test
    fun `hasInternetConnection should reflect observer state`() = runTest {
        val connectionFlow = MutableStateFlow(true)
        every { connectivityObserver.hasInternetConnection } returns connectionFlow

        coEvery { getCityByIdUseCase(any()) } returns mockk()
        coEvery { getCityWeatherUseCase(any()) } returns WeatherResult.Success(emptyList())

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        viewModel.hasInternetConnection.test {
            assertThat(awaitItem()).isTrue()

            connectionFlow.value = false
            assertThat(awaitItem()).isFalse()
        }
    }

    @Test
    fun `favoriteEvents should emit new list when use case flow emits`() = runTest {
        val favoritesFlow = MutableStateFlow<List<CityEvent>>(emptyList())
        every { getFavoriteEventsUseCase(cityId) } returns favoritesFlow

        coEvery { getCityByIdUseCase(any()) } returns mockk()
        coEvery { getCityWeatherUseCase(any()) } returns WeatherResult.Success(emptyList())

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        viewModel.favoriteEvents.test {
            assertThat(awaitItem()).isEmpty()

            val newFavorites = listOf(mockk<CityEvent>())
            favoritesFlow.value = newFavorites

            assertThat(awaitItem()).isEqualTo(newFavorites)
        }
    }

    @Test
    fun `toggleFavoriteFilter should flip boolean value`() = runTest {
        coEvery { getCityByIdUseCase(any()) } returns mockk()
        coEvery { getCityWeatherUseCase(any()) } returns WeatherResult.Success(emptyList())

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        assertThat(viewModel.favoriteFilter.value).isFalse()

        viewModel.toggleFavoriteFilter()
        assertThat(viewModel.favoriteFilter.value).isTrue()

        viewModel.toggleFavoriteFilter()
        assertThat(viewModel.favoriteFilter.value).isFalse()
    }

    @Test
    fun `manuallyRetryLoadWeather should show Loading then Success`() = runTest {
        val mockWeather = listOf(mockk<CityWeather>())
        coEvery { getCityByIdUseCase(any()) } returns mockk()

        coEvery { getCityWeatherUseCase(cityId) } returns WeatherResult.Success(mockWeather)

        viewModel = CityScreenViewModel(
            getCityWeatherUseCase,
            getCityByIdUseCase,
            getCityEventsUseCase,
            getFavoriteEventsUseCase,
            connectivityObserver,
            savedStateHandle
        )

        coEvery { getCityWeatherUseCase(cityId) } coAnswers {
            delay(10)
            WeatherResult.Success(mockWeather)
        }

        viewModel.uiState.test {
            skipItems(1)

            viewModel.manuallyRetryLoadWeather()

            val loadingState = awaitItem()
            assertThat(loadingState.weather).isInstanceOf(UiState.Loading::class.java)

            val successState = awaitItem()
            assertThat(successState.weather).isInstanceOf(UiState.Success::class.java)
        }
    }
}