package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityHistoryByCityIdUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.util.MainDispatcherExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainDispatcherExtension::class)
class CityHistoryScreenViewModelTest {

    private val getCityHistoryUseCase = mockk<GetCityHistoryByCityIdUseCase>()

    private val cityId = "auckland_history_01"
    private val savedStateHandle = SavedStateHandle(mapOf("cityId" to cityId))

    private lateinit var viewModel: CityHistoryScreenViewModel

    @Test
    fun `getCityHistory should transition from Loading to Success`() = runTest {
        val mockHistory = mockk<CityHistory>()
        coEvery { getCityHistoryUseCase(cityId) } coAnswers {
            delay(1)
            mockHistory
        }

        viewModel = CityHistoryScreenViewModel(getCityHistoryUseCase, savedStateHandle)

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiState.Loading)

            val successState = awaitItem() as UiState.Success
            assertThat(successState.data).isEqualTo(mockHistory)
        }
    }

    @Test
    fun `getCityHistory should transition from Loading to Error on failure`() = runTest {
        val exception = RuntimeException("History not found")
        coEvery { getCityHistoryUseCase(cityId) } coAnswers {
            delay(1)
            throw exception
        }

        viewModel = CityHistoryScreenViewModel(getCityHistoryUseCase, savedStateHandle)

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiState.Loading)

            val errorState = awaitItem() as UiState.Error
            assertThat(errorState.error).isEqualTo(exception)
        }
    }
}