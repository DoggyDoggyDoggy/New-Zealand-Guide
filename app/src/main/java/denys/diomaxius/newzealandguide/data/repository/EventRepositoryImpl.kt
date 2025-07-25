package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.domain.repository.EventRepository

class EventRepositoryImpl : EventRepository {
    override suspend fun getEventsInCity(cityId: String): List<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventInCity(
        cityId: String,
        eventId: String,
    ): Event {
        TODO("Not yet implemented")
    }

}