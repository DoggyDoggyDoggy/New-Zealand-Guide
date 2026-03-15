package denys.diomaxius.newzealandguide.feature_review.usecase

import android.util.Log
import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShouldShowReviewUseCase @Inject constructor(
    private val prefsManager: ReviewPreferencesManager,
) {
    operator fun invoke(): Flow<Boolean> = prefsManager.reviewDataFlow.map { data ->
        val isEnoughActions = data.count >= 12
        val isEnoughLaunches = data.launchCount >= 3
        val showDialog = data.showAgain
        val showDialogLater = data.showLater

        Log.d(
            "ShouldShowReviewUseCase",
            "isEnoughActions: $isEnoughActions, isEnoughLaunches: $isEnoughLaunches"
        )

        if (showDialog) {
            if (showDialogLater) {
                val weekInMillis = 7 * 24 * 60 * 60 * 1000L
                val isTimePassed = (System.currentTimeMillis() - data.lastPromptTime) > weekInMillis
                isTimePassed && isEnoughActions && isEnoughLaunches
            } else {
                isEnoughActions && isEnoughLaunches
            }
        } else false
    }
}