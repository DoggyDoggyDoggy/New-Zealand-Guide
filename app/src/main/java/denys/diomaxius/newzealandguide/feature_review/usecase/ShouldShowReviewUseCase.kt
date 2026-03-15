package denys.diomaxius.newzealandguide.feature_review.usecase

import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShouldShowReviewUseCase @Inject constructor(
    private val prefsManager: ReviewPreferencesManager
) {
    operator fun invoke(): Flow<Boolean> = prefsManager.reviewDataFlow.map { data ->
        val isEnoughActions = data.count >= 12
        val isEnoughLaunches = data.launchCount >= 3

        val canShow = data.showAgain && data.showLater

        val monthInMillis = 30 * 24 * 60 * 60 * 1000L
        val isTimePassed = (System.currentTimeMillis() - data.lastPromptTime) > monthInMillis

        canShow && isEnoughActions && isEnoughLaunches && isTimePassed
    }
}