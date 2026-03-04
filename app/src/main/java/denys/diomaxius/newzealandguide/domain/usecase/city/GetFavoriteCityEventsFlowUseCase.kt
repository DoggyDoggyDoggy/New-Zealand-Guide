package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetFavoriteCityEventsFlowUseCase @Inject constructor(
    private val cityRepository: CityRepository,
) {
    operator fun invoke(cityId: String) =
        cityRepository.getFavoriteCityEventsFlow(cityId)
}