package denys.diomaxius.newzealandguide.data.repository

import app.cash.turbine.test
import denys.diomaxius.newzealandguide.data.local.datastore.OnboardingManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.verify

class OnboardingRepositoryImplTest {
    private val onboardingManager = mockk<OnboardingManager>()

    private lateinit var repository: OnboardingRepositoryImpl

    @BeforeEach
    fun setup() {
        repository = OnboardingRepositoryImpl(onboardingManager)
    }

    @Test
    fun `getOnboardingStatus should emit value from manager`() = runTest {
        every { onboardingManager.isOnboardingCompleted } returns flowOf(true)

        repository.getOnboardingStatus().test {
            val item = awaitItem()
            assertThat(item).isTrue()
            awaitComplete()
        }

        verify { onboardingManager.isOnboardingCompleted }
    }

    @Test
    fun `saveOnboardingStatus should call manager save method`() = runTest {
        coEvery { onboardingManager.saveOnboardingStatus() } returns Unit

        repository.saveOnboardingStatus()

        coVerify(exactly = 1) { onboardingManager.saveOnboardingStatus() }
    }
}