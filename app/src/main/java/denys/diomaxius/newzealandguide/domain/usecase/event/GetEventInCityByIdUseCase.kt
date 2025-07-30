package denys.diomaxius.newzealandguide.domain.usecase.event

import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import javax.inject.Inject

class GetEventInCityByIdUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(cityId: String, eventId: String) =
        eventRepository.getEventInCity(cityId = cityId, eventId = eventId)
}