package denys.diomaxius.newzealandguide.ui.screen.maoriwords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords
import denys.diomaxius.newzealandguide.domain.usecase.maoriwords.GetMaoriWordsUseCase
import denys.diomaxius.newzealandguide.ui.common.uistate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaoriWordsScreenViewModel @Inject constructor(
    private val getMaoriWordsUseCase: GetMaoriWordsUseCase,
) : ViewModel() {

    private val _maoriWordsUiState = MutableStateFlow<UiState<MaoriWords>>(UiState.Loading)
    val maoriWordsUiState = _maoriWordsUiState

    init {
        getMaoriWords()
    }

    private fun getMaoriWords() = viewModelScope.launch {
        _maoriWordsUiState.value = try {
            UiState.Success(getMaoriWordsUseCase())
        } catch (e: Exception) {
            UiState.Error(e)
        }
    }
}