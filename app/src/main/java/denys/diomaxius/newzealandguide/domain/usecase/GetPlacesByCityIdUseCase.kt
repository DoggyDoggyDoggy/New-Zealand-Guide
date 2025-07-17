package denys.diomaxius.newzealandguide.domain.usecase

import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetPlacesByCityIdUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(id: String): List<CityPlaceTopic> = cityRepository.getPlacesByCityId(id)
}