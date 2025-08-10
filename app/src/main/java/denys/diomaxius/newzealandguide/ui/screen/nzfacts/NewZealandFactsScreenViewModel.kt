package denys.diomaxius.newzealandguide.ui.screen.nzfacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.domain.usecase.nzfacts.GetNewZealandFactsUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewZealandFactsScreenViewModel @Inject constructor(
    private val getNewZealandFactsUseCase: GetNewZealandFactsUseCase
): ViewModel() {
    private val _nzFactsUiState = MutableStateFlow<UiState<NewZealandFacts>>(UiState.Loading)
    val nzFactsUiState = _nzFactsUiState

    init {
        loadNewZealandFacts()
    }

    private fun loadNewZealandFacts() = viewModelScope.launch {
        _nzFactsUiState.value = try {
            UiState.Success(getNewZealandFactsUseCase())
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}