package denys.diomaxius.newzealandguide.feature_review.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import denys.diomaxius.newzealandguide.feature_review.data.ReviewPrefs
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ShouldShowReviewUseCaseTest {

    private val prefsManager = mockk<ReviewPreferencesManager>()
    private lateinit var useCase: ShouldShowReviewUseCase

    private val dataFlow = MutableStateFlow(
        ReviewPrefs(
            count = 0,
            lastPromptTime = 0L,
            showAgain = true,
            showLater = false,
            launchCount = 0
        )
    )

    private val weekInMillis = 7 * 24 * 60 * 60 * 1000L

    @BeforeEach
    fun setup() {
        useCase = ShouldShowReviewUseCase(prefsManager)
        every { prefsManager.reviewDataFlow } returns dataFlow
    }

    @Test
    fun `should return false if showAgain is false`() = runTest {
        dataFlow.value = dataFlow.value.copy(
            showAgain = false,
            count = 50,
            launchCount = 20
        )

        useCase().test {
            assertThat(awaitItem()).isFalse()
        }
    }

    @Test
    fun `should return true only when action and launch counts are met`() = runTest {
        dataFlow.value = dataFlow.value.copy(count = 11, launchCount = 5)
        useCase().test { assertThat(awaitItem()).isFalse() }

        dataFlow.value = dataFlow.value.copy(count = 15, launchCount = 2)
        useCase().test { assertThat(awaitItem()).isFalse() }

        dataFlow.value = dataFlow.value.copy(count = 12, launchCount = 3)
        useCase().test { assertThat(awaitItem()).isTrue() }
    }

    @Test
    fun `should enforce one week delay if showLater is true`() = runTest {
        val now = System.currentTimeMillis()

        dataFlow.value = dataFlow.value.copy(
            count = 12,
            launchCount = 3,
            showLater = true,
            lastPromptTime = now
        )

        useCase().test {
            assertThat(awaitItem()).isFalse()
        }

        dataFlow.value = dataFlow.value.copy(
            lastPromptTime = now - (weekInMillis + 60000)
        )

        useCase().test {
            assertThat(awaitItem()).isTrue()
        }
    }

    @Test
    fun `should return true if counts are met and showLater is false`() = runTest {
        dataFlow.value = dataFlow.value.copy(
            count = 12,
            launchCount = 3,
            showLater = false,
            lastPromptTime = 0L
        )

        useCase().test {
            assertThat(awaitItem()).isTrue()
        }
    }
}