package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCitiesPagerFlowUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(pageSize: Int) =
        cityRepository.citiesPagerFlow(pageSize)
}