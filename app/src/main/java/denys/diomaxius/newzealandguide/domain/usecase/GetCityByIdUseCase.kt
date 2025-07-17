package denys.diomaxius.newzealandguide.domain.usecase

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCityByIdUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(id: String): City = cityRepository.getCityById(id)
}