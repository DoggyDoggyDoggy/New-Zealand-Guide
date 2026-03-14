package denys.diomaxius.newzealandguide.feature_review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import denys.diomaxius.newzealandguide.feature_review.data.FeedbackRepository
import denys.diomaxius.newzealandguide.feature_review.ui.models.ReviewAction
import denys.diomaxius.newzealandguide.feature_review.ui.models.ReviewEvent
import denys.diomaxius.newzealandguide.feature_review.ui.models.ReviewUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val repository: FeedbackRepository = FeedbackRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(ReviewUiState())
    val state = _state.asStateFlow()

    private val _events = Channel<ReviewEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: ReviewAction) {
        when (action) {
            is ReviewAction.SelectRating -> handleRating(action.rating)
            is ReviewAction.UpdateFeedback -> _state.update { it.copy(feedbackText = action.text) }
            ReviewAction.SubmitFeedback -> sendFeedbackToFirebase()
        }
    }

    private fun handleRating(rating: Int) {
        _state.update { it.copy(selectedRating = rating) }

        if (rating >= 4) {
            viewModelScope.launch {
                _events.send(ReviewEvent.LaunchGooglePlayReview)
            }
        } else {
            _state.update { it.copy(showFeedbackForm = true) }
        }
    }

    private fun sendFeedbackToFirebase() {
        val currentState = _state.value
        if (currentState.feedbackText.isBlank()) return

        _state.update { it.copy(isSubmitting = true) }

        viewModelScope.launch {
            val result = repository.sendComplaint(
                rating = currentState.selectedRating,
                feedback = currentState.feedbackText
            )

            result.onSuccess {
                _state.update { it.copy(isSubmitting = false, isSuccess = true, showFeedbackForm = false) }
            }.onFailure { error ->
                _state.update { it.copy(isSubmitting = false) }
                _events.send(ReviewEvent.ShowError(error.message ?: "Ошибка отправки"))
            }
        }
    }
}