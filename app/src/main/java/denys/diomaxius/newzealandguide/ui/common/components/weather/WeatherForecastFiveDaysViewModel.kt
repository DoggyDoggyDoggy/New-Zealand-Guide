package denys.diomaxius.newzealandguide.ui.common.components.weather

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.GetWeatherByCityIdUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherForecastFiveDaysViewModel @Inject constructor(
    private val getWeatherByCityIdUseCase: GetWeatherByCityIdUseCase
): ViewModel() {

}