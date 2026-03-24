package denys.diomaxius.newzealandguide.ui.screen.event

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.just
import io.mockk.Runs

import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest

import app.cash.turbine.test

import androidx.lifecycle.SavedStateHandle
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.usecase.city.GetCityEventUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.ToggleEventFavoriteUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState

import denys.diomaxius.newzealandguide.util.MainDispatcherExtension
import kotlinx.coroutines.delay

@ExtendWith(MainDispatcherExtension::class)
class EventDetailsScreenViewModelTest {
    private val getCityEventUseCase = mockk<GetCityEventUseCase>()
    private val toggleEventFavoriteUseCase = mockk<ToggleEventFavoriteUseCase>()
    private val incrementReviewActionUseCase = mockk<IncrementReviewActionUseCase>()

    private val cityId = "nz_city_1"
    private val eventId = "rugby_event_99"

    private val savedStateHandle = SavedStateHandle(
        mapOf("cityId" to cityId, "eventId" to eventId)
    )

    private lateinit var viewModel: EventDetailsScreenViewModel

    @BeforeEach
    fun setup() {
        coEvery { incrementReviewActionUseCase() } just Runs
    }

    @Test
    fun `uiState should transition from Loading to Success`() = runTest {
        val mockEvent = mockk<CityEvent>()

        every { getCityEventUseCase(cityId, eventId) } returns flow {
            delay(1)
            emit(mockEvent)
        }

        viewModel = EventDetailsScreenViewModel(
            getCityEventUseCase,
            toggleEventFavoriteUseCase,
            incrementReviewActionUseCase,
            savedStateHandle
        )

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(UiState.Loading::class.java)

            val successState = awaitItem() as UiState.Success
            assertThat(successState.data).isEqualTo(mockEvent)
        }
    }

    @Test
    fun `uiState should transition to Error when use case fails`() = runTest {
        val error = RuntimeException("Database error")

        every { getCityEventUseCase(cityId, eventId) } returns flow {
            delay(1)
            throw error
        }

        viewModel = EventDetailsScreenViewModel(
            getCityEventUseCase,
            toggleEventFavoriteUseCase,
            incrementReviewActionUseCase,
            savedStateHandle
        )

        viewModel.uiState.test {
            assertThat(awaitItem()).isInstanceOf(UiState.Loading::class.java)

            val errorState = awaitItem() as UiState.Error
            assertThat(errorState.error).isEqualTo(error)
        }
    }

    @Test
    fun `toggleFavorite should trigger increment and toggle use cases`() = runTest {
        every { getCityEventUseCase(any(), any()) } returns emptyFlow()
        coEvery { toggleEventFavoriteUseCase(any(), any()) } just Runs

        viewModel = EventDetailsScreenViewModel(
            getCityEventUseCase,
            toggleEventFavoriteUseCase,
            incrementReviewActionUseCase,
            savedStateHandle
        )

        viewModel.toggleFavorite()

        coVerify(exactly = 1) { toggleEventFavoriteUseCase(cityId, eventId) }
        coVerify(exactly = 2) { incrementReviewActionUseCase() }
    }
}