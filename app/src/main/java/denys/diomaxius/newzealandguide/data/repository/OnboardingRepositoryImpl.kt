package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.data.local.datastore.OnboardingManager
import denys.diomaxius.newzealandguide.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val onboardingManager: OnboardingManager,
) : OnboardingRepository {
    override fun getOnboardingStatus() = onboardingManager.isOnboardingCompleted
    override suspend fun saveOnboardingStatus() = onboardingManager.saveOnboardingStatus()
}