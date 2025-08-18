package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
) {
    suspend operator fun invoke(): List<City> =
        cityRepository.getAllCities().sortedBy { it.name }
}