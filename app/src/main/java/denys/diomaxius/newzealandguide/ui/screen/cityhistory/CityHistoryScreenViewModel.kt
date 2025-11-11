package denys.diomaxius.newzealandguide.ui.screen.cityhistory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityHistoryScreenViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val cityId: String = checkNotNull(savedStateHandle["cityId"])

    init {
        getCityHistory()
    }

    private fun getCityHistory() = viewModelScope.launch {

    }
}