package denys.diomaxius.newzealandguide.domain.usecase.cityplaces

import denys.diomaxius.newzealandguide.domain.repository.CityPlacesRepository
import javax.inject.Inject

class GetPlacesForCityByIdUseCase @Inject constructor(
    private val cityPlacesRepository: CityPlacesRepository
) {
    suspend operator fun invoke(cityId: String) =
        cityPlacesRepository.getPlacesForCityById(cityId)
}