package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.home.GetHomeDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {
    val homeData = getHomeDataUseCase()
}