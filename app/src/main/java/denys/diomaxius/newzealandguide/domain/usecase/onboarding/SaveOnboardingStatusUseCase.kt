package denys.diomaxius.newzealandguide.domain.usecase.onboarding

import denys.diomaxius.newzealandguide.domain.repository.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingStatusUseCase @Inject constructor(
    private val repository: OnboardingRepository,
) {
    suspend operator fun invoke() = repository.saveOnboardingStatus()
}