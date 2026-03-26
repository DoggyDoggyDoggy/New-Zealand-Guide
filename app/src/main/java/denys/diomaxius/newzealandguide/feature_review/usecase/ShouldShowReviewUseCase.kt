package denys.diomaxius.newzealandguide.feature_review.usecase

import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val weekInMillis = 7 * 24 * 60 * 60 * 1000L

class ShouldShowReviewUseCase @Inject constructor(
    private val prefsManager: ReviewPreferencesManager,
) {
    operator fun invoke(): Flow<Boolean> = prefsManager.reviewDataFlow.map { data ->
        if (!data.showAgain) return@map false

        val isEnoughActions = data.count >= 12
        val isEnoughLaunches = data.launchCount >= 3

        val isTimePassed = if (data.showLater) {
            (System.currentTimeMillis() - data.lastPromptTime) > weekInMillis
        } else {
            true
        }

        isEnoughActions && isEnoughLaunches && isTimePassed
    }
}