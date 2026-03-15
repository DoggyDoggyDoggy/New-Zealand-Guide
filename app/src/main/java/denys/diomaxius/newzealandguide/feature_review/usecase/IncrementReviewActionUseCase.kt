package denys.diomaxius.newzealandguide.feature_review.usecase

import android.util.Log
import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import javax.inject.Inject

class IncrementReviewActionUseCase @Inject constructor(
    private val repository: ReviewPreferencesManager
) {
    suspend operator fun invoke() {
        Log.d("feature_review","IncrementReviewAction")
        repository.incrementActionCount()
    }
}