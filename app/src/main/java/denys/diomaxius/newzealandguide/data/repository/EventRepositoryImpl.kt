package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.mapper.event.getImageUrl
import denys.diomaxius.newzealandguide.data.mapper.event.parseSessions
import denys.diomaxius.newzealandguide.data.mapper.event.toDomain
import denys.diomaxius.newzealandguide.data.model.event.EventDto
import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import kotlinx.coroutines.tasks.await

class EventRepositoryImpl(
    private val firestore: FirebaseFirestore
) : EventRepository {
    override suspend fun getEventsInCity(cityId: String): List<Event> {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("events")
            .get()
            .await()
        return snap.documents.mapNotNull { doc ->
            EventDto(
                id = doc.id,
                url = doc.getString("url") ?: "",
                name = doc.getString("name") ?: "",
                description = doc.getString("description") ?: "",
                address = doc.getString("address") ?: "",
                sessions = emptyList(),
                imageUrl = getImageUrl(doc)
            ).toDomain()
        }
    }

    override suspend fun getEventInCity(
        cityId: String,
        eventId: String,
    ): Event {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("events")
            .document(eventId)
            .get()
            .await()
        if (!snap.exists()) throw Exception("Event not found")

        return EventDto(
            id = snap.id,
            url = snap.getString("url") ?: "",
            name = snap.getString("name") ?: "",
            description = snap.getString("description") ?: "",
            address = snap.getString("address") ?: "",
            sessions = parseSessions(snap),
            imageUrl = getImageUrl(snap)
        ).toDomain()
    }
}