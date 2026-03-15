package denys.diomaxius.newzealandguide.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.onboarding.GetOnboardingStatusUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementAppLaunchUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.ShouldShowReviewUseCase
import denys.diomaxius.newzealandguide.navigation.NavScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOnboardingStatusUseCase: GetOnboardingStatusUseCase,
    private val incrementAppLaunchUseCase: IncrementAppLaunchUseCase,
    private val shouldShowReviewUseCase: ShouldShowReviewUseCase,
) : ViewModel() {
    private val _showReviewDialog = MutableStateFlow(false)
    val showReviewDialog = _showReviewDialog.asStateFlow()

    var startDestination = mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            getOnboardingStatusUseCase().collect { isCompleted ->
                startDestination.value = if (isCompleted) {
                    NavScreen.Home.route
                } else {
                    NavScreen.Onboarding.route
                }
            }
        }

        viewModelScope.launch {
            incrementAppLaunchUseCase()
        }

        checkReviewCondition()
    }

    private fun checkReviewCondition() {
        viewModelScope.launch {
            shouldShowReviewUseCase().collect { shouldShow ->
                if (shouldShow) {
                    _showReviewDialog.value = true
                }
            }
        }
    }

    fun onReviewDismissed() {
        _showReviewDialog.value = false
    }
}