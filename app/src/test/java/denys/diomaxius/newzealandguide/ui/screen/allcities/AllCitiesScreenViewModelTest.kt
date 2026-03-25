package denys.diomaxius.newzealandguide.ui.screen.allcities

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.city.GetAllCitiesFlowUseCase
import denys.diomaxius.newzealandguide.domain.usecase.city.ToggleFavoriteUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import denys.diomaxius.newzealandguide.util.MainDispatcherExtension
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherExtension::class)
class AllCitiesScreenViewModelTest {

    private val getAllCitiesFlowUseCase = mockk<GetAllCitiesFlowUseCase>()
    private val toggleFavoriteUseCase = mockk<ToggleFavoriteUseCase>()
    private val incrementReviewActionUseCase = mockk<IncrementReviewActionUseCase>()

    private lateinit var viewModel: AllCitiesScreenViewModel

    @Test
    fun `cities flows should emit data from use case`() = runTest {
        val allCitiesList = listOf(mockk<City>())
        val favoriteCitiesList = listOf(mockk<City>())

        every { getAllCitiesFlowUseCase(onlyFavorites = false) } returns flowOf(allCitiesList)
        every { getAllCitiesFlowUseCase(onlyFavorites = true) } returns flowOf(favoriteCitiesList)

        viewModel = AllCitiesScreenViewModel(
            getAllCitiesFlowUseCase,
            toggleFavoriteUseCase,
            incrementReviewActionUseCase
        )

        viewModel.allCities.test {
            assertThat(awaitItem()).isEqualTo(allCitiesList)
        }

        viewModel.favoriteCities.test {
            assertThat(awaitItem()).isEqualTo(favoriteCitiesList)
        }
    }

    @Test
    fun `toggleFavorite should trigger toggle and review increment`() = runTest {
        val cityId = "city_123"
        every { getAllCitiesFlowUseCase(any()) } returns emptyFlow()
        coEvery { toggleFavoriteUseCase(any()) } just Runs
        coEvery { incrementReviewActionUseCase() } just Runs

        viewModel = AllCitiesScreenViewModel(
            getAllCitiesFlowUseCase,
            toggleFavoriteUseCase,
            incrementReviewActionUseCase
        )

        viewModel.toggleFavorite(cityId)

        coVerify(exactly = 1) { toggleFavoriteUseCase(cityId) }
        coVerify(exactly = 1) { incrementReviewActionUseCase() }
    }

    @Test
    fun `toggleFavoriteFilter should change state`() = runTest {
        every { getAllCitiesFlowUseCase(any()) } returns emptyFlow()

        viewModel = AllCitiesScreenViewModel(
            getAllCitiesFlowUseCase,
            toggleFavoriteUseCase,
            incrementReviewActionUseCase
        )

        assertThat(viewModel.favoriteFilter.value).isFalse()

        viewModel.toggleFavoriteFilter()
        assertThat(viewModel.favoriteFilter.value).isTrue()
    }
}