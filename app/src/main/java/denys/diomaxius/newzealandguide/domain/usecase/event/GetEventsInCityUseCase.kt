package denys.diomaxius.newzealandguide.domain.usecase.event

import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import javax.inject.Inject

class GetEventsInCityUseCase @Inject constructor(
    private val eventRepository: EventRepository
){
    suspend operator fun invoke(cityId: String) = eventRepository.getEventsInCity(cityId)
}