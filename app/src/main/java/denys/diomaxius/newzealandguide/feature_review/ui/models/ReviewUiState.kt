package denys.diomaxius.newzealandguide.feature_review.ui.models

data class ReviewUiState(
    val selectedRating: Int = 0,
    val showFeedbackForm: Boolean = false,
    val feedbackText: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false
)