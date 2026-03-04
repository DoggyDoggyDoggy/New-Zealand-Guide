package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCityEventUseCase @Inject constructor(
    private val cityRepository: CityRepository,
) {
    operator fun invoke(cityId: String, eventId: String) =
        cityRepository.getCityEvent(cityId, eventId)
}