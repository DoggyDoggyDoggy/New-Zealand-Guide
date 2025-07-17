package denys.diomaxius.newzealandguide.ui.allcities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.usecase.GetAllCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCitiesScreenViewModel @Inject constructor(
    private val getAllCitiesUseCase: GetAllCitiesUseCase
) : ViewModel() {
    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    init {
        getAllCities()
    }

    private fun getAllCities() {
        viewModelScope.launch {
            _cities.value = getAllCitiesUseCase()
        }
    }
}