package denys.diomaxius.newzealandguide.feature_review.ui.models

sealed interface ReviewAction {
    data class SelectRating(val rating: Int) : ReviewAction
    data class UpdateFeedback(val text: String) : ReviewAction
    object SubmitFeedback : ReviewAction
}