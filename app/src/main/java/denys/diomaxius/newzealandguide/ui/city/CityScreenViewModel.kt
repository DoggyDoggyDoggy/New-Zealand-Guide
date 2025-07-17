package denys.diomaxius.newzealandguide.ui.city

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.GetCityByIdUseCase
import javax.inject.Inject

@HiltViewModel
class CityScreenViewModel @Inject constructor(
    private val getCityByIdUseCase: GetCityByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val cityId: String = checkNotNull(savedStateHandle["cityId"])


}