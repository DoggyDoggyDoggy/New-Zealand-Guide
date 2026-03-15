package denys.diomaxius.newzealandguide.feature_review.usecase

import denys.diomaxius.newzealandguide.feature_review.data.ReviewPreferencesManager
import javax.inject.Inject

class DontShowDialogAgainUseCase @Inject constructor(
    private val repository: ReviewPreferencesManager
) {
    suspend operator fun invoke() = repository.dontShowDialogAgain()
}