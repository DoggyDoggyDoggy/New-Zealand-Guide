package denys.diomaxius.newzealandguide.ui.screen.maoriwords

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.maoriwords.GetMaoriWordsUseCase
import javax.inject.Inject

@HiltViewModel
class MaoriWordsScreenViewModel @Inject constructor(
    getMaoriWordsUseCase: GetMaoriWordsUseCase,
) : ViewModel() {
    val maoriWords = getMaoriWordsUseCase()
}