package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.domain.usecase.nzhistory.GetNewZealandHistoryUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewZealandHistoryScreenViewModel @Inject constructor(
    private val getNewZealandHistoryUseCase: GetNewZealandHistoryUseCase,
) : ViewModel() {
    private val _nzHistoryUiState = MutableStateFlow<UiState<NewZealandHistory>>(UiState.Loading)
    val nzHistoryUiState = _nzHistoryUiState

    init {
        loadNewZealandHistory()
    }

    private fun loadNewZealandHistory() = viewModelScope.launch {
        _nzHistoryUiState.value = try {
            UiState.Success(getNewZealandHistoryUseCase())
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}