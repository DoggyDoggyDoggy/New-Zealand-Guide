package denys.diomaxius.newzealandguide.ui.screen.maorihubscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.nzfacts.GetNewZealandFactsUseCase
import javax.inject.Inject

@HiltViewModel
class MaoriHubScreenViewModel @Inject constructor(
    getNewZealandFactsUseCase: GetNewZealandFactsUseCase,
) : ViewModel() {
    val randomNzFact = getNewZealandFactsUseCase().getRandomFact()
}