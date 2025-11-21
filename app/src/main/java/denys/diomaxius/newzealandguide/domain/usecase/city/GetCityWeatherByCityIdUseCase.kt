package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.city.CityWeatherRepository
import javax.inject.Inject

class GetCityWeatherByCityIdUseCase  @Inject constructor(
    private val repository: CityWeatherRepository
) {
    suspend operator fun invoke(cityId: String) = repository.getCityWeatherByCityId(cityId)
}