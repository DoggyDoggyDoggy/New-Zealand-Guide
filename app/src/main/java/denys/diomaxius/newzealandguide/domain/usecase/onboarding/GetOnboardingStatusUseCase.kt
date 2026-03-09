package denys.diomaxius.newzealandguide.domain.usecase.onboarding

import denys.diomaxius.newzealandguide.domain.repository.OnboardingRepository
import javax.inject.Inject

class GetOnboardingStatusUseCase @Inject constructor(
    private val repository: OnboardingRepository,
) {
    operator fun invoke() = repository.getOnboardingStatus()
}