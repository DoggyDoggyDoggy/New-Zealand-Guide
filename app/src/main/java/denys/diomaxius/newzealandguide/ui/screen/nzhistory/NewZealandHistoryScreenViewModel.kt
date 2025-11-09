package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.nzhistory.GetNewZealandHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class NewZealandHistoryScreenViewModel @Inject constructor(
    getNewZealandHistoryUseCase: GetNewZealandHistoryUseCase,
) : ViewModel() {
    val newZealandHistory = getNewZealandHistoryUseCase()
}