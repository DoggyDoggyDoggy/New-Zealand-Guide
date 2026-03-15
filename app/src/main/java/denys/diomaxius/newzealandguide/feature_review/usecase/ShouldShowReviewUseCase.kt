package denys.diomaxius.newzealandguide.feature_review.usecase

import android.util.Log
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

        Log.d("ShouldShowReviewUseCase", "isEnoughActions: $isEnoughActions, isEnoughLaunches: $isEnoughLaunches")

        isEnoughActions && isEnoughLaunches
    }
}