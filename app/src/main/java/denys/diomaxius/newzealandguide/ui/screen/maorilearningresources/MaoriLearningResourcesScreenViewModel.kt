package denys.diomaxius.newzealandguide.ui.screen.maorilearningresources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.maorihub.GetMaoriLearningResourcesUseCase
import denys.diomaxius.newzealandguide.feature_review.usecase.IncrementReviewActionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaoriLearningResourcesScreenViewModel @Inject constructor(
    getMaoriLearningResourcesUseCase: GetMaoriLearningResourcesUseCase,
    private val incrementReviewActionUseCase: IncrementReviewActionUseCase
) : ViewModel() {
    val maoriLearningResources = getMaoriLearningResourcesUseCase()

    init {
        increaseReviewAction()
    }

    private fun increaseReviewAction() = viewModelScope.launch {
        incrementReviewActionUseCase()
    }
}