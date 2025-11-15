package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherByCityIdUseCase  @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityId: String) = repository.getCityWeatherByCityId(cityId)
}