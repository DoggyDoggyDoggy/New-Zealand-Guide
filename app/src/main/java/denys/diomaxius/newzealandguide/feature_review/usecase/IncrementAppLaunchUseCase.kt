package denys.diomaxius.newzealandguide.feature_review.usecase

import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import javax.inject.Inject

class IncrementAppLaunchUseCase @Inject constructor(
    private val prefs: ReviewPreferencesManager
) {
    companion object {
        private var isCountedInThisProcess = false
    }

    suspend operator fun invoke() {
        if (!isCountedInThisProcess) {
            prefs.incrementLaunchCount()
            isCountedInThisProcess = true
        }
    }
}