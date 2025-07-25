package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.event.Event

interface EventRepository {
    suspend fun getEventsInCity(cityId: String): List<Event>
    suspend fun getEventInCity(cityId: String, eventId: String): Event
}