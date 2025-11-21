package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.city.CityRepository
import javax.inject.Inject

class GetCityByIdUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
   suspend operator fun invoke(cityId: String) =
        cityRepository.getCityById(cityId)
}