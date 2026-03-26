package denys.diomaxius.newzealandguide.feature_review.ui

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import io.mockk.just
import io.mockk.Runs

import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import app.cash.turbine.test
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper
import denys.diomaxius.newzealandguide.feature_review.data.FeedbackRepository
import denys.diomaxius.newzealandguide.feature_review.usecase.DontShowDialogAgainUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.SetShowDialogLaterUseCase

import denys.diomaxius.newzealandguide.util.MainDispatcherExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherExtension::class)
class ReviewViewModelTest {

    private val repository = mockk<FeedbackRepository>()
    private val dontShowAgainUseCase = mockk<DontShowDialogAgainUseCase>()
    private val setLaterUseCase = mockk<SetShowDialogLaterUseCase>()
    private val analyticsHelper = mockk<AnalyticsHelper>(relaxed = true)

    private lateinit var viewModel: ReviewViewModel

    @BeforeEach
    fun setup() {
        viewModel = ReviewViewModel(
            repository,
            dontShowAgainUseCase,
            setLaterUseCase,
            analyticsHelper
        )
    }

    @Test
    fun `high rating should trigger Google Play review event`() = runTest {
        viewModel.events.test {
            viewModel.onAction(ReviewAction.SelectRating(5))

            assertThat(awaitItem()).isEqualTo(ReviewEvent.LaunchGooglePlayReview)

            assertThat(viewModel.state.value.selectedRating).isEqualTo(5)
            assertThat(viewModel.state.value.showFeedbackForm).isFalse()

            verify { analyticsHelper.logEvent("review_positive_intent", any()) }
        }
    }

    @Test
    fun `low rating should show feedback form`() = runTest {
        viewModel.onAction(ReviewAction.SelectRating(3))

        assertThat(viewModel.state.value.selectedRating).isEqualTo(3)
        assertThat(viewModel.state.value.showFeedbackForm).isTrue()

        verify { analyticsHelper.logEvent("review_negative_intent", any()) }
    }

    @Test
    fun `submit feedback success should update state`() = runTest {
        val feedback = "Everything is great!"
        val rating = 3

        coEvery { repository.sendComplaint(rating, feedback) } returns Result.success(Unit)

        viewModel.onAction(ReviewAction.SelectRating(rating))
        viewModel.onAction(ReviewAction.UpdateFeedback(feedback))

        viewModel.state.test {
            assertThat(awaitItem().isSubmitting).isFalse()

            viewModel.onAction(ReviewAction.SubmitFeedback)

            assertThat(awaitItem().isSubmitting).isTrue()

            val finalState = awaitItem()
            assertThat(finalState.isSubmitting).isFalse()
            assertThat(finalState.isSuccess).isTrue()
            assertThat(finalState.showFeedbackForm).isFalse()
        }
    }

    @Test
    fun `submit feedback failure should send error event`() = runTest {
        val errorMsg = "Network error"
        coEvery { repository.sendComplaint(any(), any()) } returns Result.failure(Exception(errorMsg))

        viewModel.onAction(ReviewAction.SelectRating(2))
        viewModel.onAction(ReviewAction.UpdateFeedback("Bad app"))

        viewModel.events.test {
            viewModel.onAction(ReviewAction.SubmitFeedback)

            val event = awaitItem() as ReviewEvent.ShowError
            assertThat(event.message).isEqualTo(errorMsg)
        }
    }

    @Test
    fun `helper use cases should be triggered`() = runTest {
        coEvery { dontShowAgainUseCase() } just Runs
        coEvery { setLaterUseCase() } just Runs

        viewModel.dontShowDialogAgain()
        coVerify(exactly = 1) { dontShowAgainUseCase() }

        viewModel.setShowDialogLater()
        coVerify(exactly = 1) { setLaterUseCase() }
    }
}