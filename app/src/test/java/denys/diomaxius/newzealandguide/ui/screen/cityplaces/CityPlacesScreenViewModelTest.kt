package denys.diomaxius.newzealandguide.ui.screen.cityplaces

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.just
import io.mockk.Runs

import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.delay

import app.cash.turbine.test

import androidx.lifecycle.SavedStateHandle
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.usecase.city.GetPlacesForCityByIdUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.util.MainDispatcherExtension

@ExtendWith(MainDispatcherExtension::class)
class CityPlacesScreenViewModelTest {

    private val getPlacesUseCase = mockk<GetPlacesForCityByIdUseCase>()
    private val incrementReviewUseCase = mockk<IncrementReviewActionUseCase>()

    private val cityId = "queenstown_01"
    private val savedStateHandle = SavedStateHandle(mapOf("cityId" to cityId))

    private lateinit var viewModel: CityPlacesScreenViewModel

    @BeforeEach
    fun setup() {
        coEvery { incrementReviewUseCase() } just Runs
    }

    @Test
    fun `loadPlaces should update uiState to Success when data is fetched`() = runTest {
        val mockPlaces = listOf(mockk<CityPlace>(), mockk<CityPlace>())

        coEvery { getPlacesUseCase(cityId) } coAnswers {
            delay(1)
            mockPlaces
        }

        viewModel = CityPlacesScreenViewModel(
            getPlacesUseCase,
            incrementReviewUseCase,
            savedStateHandle
        )

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiState.Loading)

            val successState = awaitItem() as UiState.Success
            assertThat(successState.data).isEqualTo(mockPlaces)
        }
    }

    @Test
    fun `loadPlaces should update uiState to Error when exception occurs`() = runTest {
        val exception = RuntimeException("No internet")

        coEvery { getPlacesUseCase(cityId) } coAnswers {
            delay(1)
            throw exception
        }

        viewModel =
            CityPlacesScreenViewModel(
                getPlacesUseCase,
                incrementReviewUseCase,
                savedStateHandle
            )

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiState.Loading)

            val errorState = awaitItem() as UiState.Error
            assertThat(errorState.error).isEqualTo(exception)
        }
    }

    @Test
    fun `init should trigger incrementReviewAction`() = runTest {
        coEvery { getPlacesUseCase(any()) } returns emptyList()

        viewModel =
            CityPlacesScreenViewModel(
                getPlacesUseCase,
                incrementReviewUseCase,
                savedStateHandle
            )

        coVerify(exactly = 1) { incrementReviewUseCase() }
    }
}