package denys.diomaxius.newzealandguide.feature_review.ui.models

sealed interface ReviewEvent {
    object LaunchGooglePlayReview : ReviewEvent
    data class ShowError(val message: String) : ReviewEvent
}