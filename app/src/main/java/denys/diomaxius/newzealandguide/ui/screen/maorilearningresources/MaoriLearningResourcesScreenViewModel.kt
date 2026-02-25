package denys.diomaxius.newzealandguide.ui.screen.maorilearningresources

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.maorihub.GetMaoriLearningResourcesUseCase
import javax.inject.Inject

@HiltViewModel
class MaoriLearningResourcesScreenViewModel @Inject constructor(
    getMaoriLearningResourcesUseCase: GetMaoriLearningResourcesUseCase
) : ViewModel() {
    val maoriLearningResources = getMaoriLearningResourcesUseCase()
}