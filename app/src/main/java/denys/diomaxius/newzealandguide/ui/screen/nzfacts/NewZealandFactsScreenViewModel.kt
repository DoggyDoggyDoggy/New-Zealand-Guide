package denys.diomaxius.newzealandguide.ui.screen.nzfacts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.nzfacts.GetNewZealandFactsUseCase
import javax.inject.Inject

@HiltViewModel
class NewZealandFactsScreenViewModel @Inject constructor(
    getNewZealandFactsUseCase: GetNewZealandFactsUseCase
): ViewModel() {
    val newZealandFacts = getNewZealandFactsUseCase()
}