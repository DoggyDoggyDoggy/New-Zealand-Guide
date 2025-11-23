package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCityWeatherByCityIdUseCase  @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(cityId: String) = cityRepository.getCityWeatherByCityId(cityId)
}