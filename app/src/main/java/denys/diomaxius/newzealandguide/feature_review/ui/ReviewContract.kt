package denys.diomaxius.newzealandguide.feature_review.ui

data class ReviewUiState(
    val selectedRating: Int = 0,
    val showFeedbackForm: Boolean = false,
    val feedbackText: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false
)

sealed interface ReviewAction {
    data class SelectRating(val rating: Int) : ReviewAction
    data class UpdateFeedback(val text: String) : ReviewAction
    object SubmitFeedback : ReviewAction
}

sealed interface ReviewEvent {
    object LaunchGooglePlayReview : ReviewEvent
    data class ShowError(val message: String) : ReviewEvent
}