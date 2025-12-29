package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCityEventsByIdUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(pageSize: Int, cityId: String) =
        cityRepository.cityEventsPagerFlow(pageSize, cityId)
}