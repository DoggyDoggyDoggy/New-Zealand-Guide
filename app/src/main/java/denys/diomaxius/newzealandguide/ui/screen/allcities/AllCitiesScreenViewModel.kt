package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.GetCitiesPagerFlowUseCase
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    getCitiesPagerFlowUseCase: GetCitiesPagerFlowUseCase
) : ViewModel() {
    val lazyCities = getCitiesPagerFlowUseCase(5).cachedIn(viewModelScope)
}