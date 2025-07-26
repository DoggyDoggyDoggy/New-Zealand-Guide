package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.event.toDomain
import denys.diomaxius.newzealandguide.data.model.event.EventDto
import denys.diomaxius.newzealandguide.domain.model.event.Event
import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import kotlinx.coroutines.tasks.await

class EventRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore,
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
                imageUrl = ""
            ).toDomain()
        }
    }

    override suspend fun getEventInCity(
        cityId: String,
        eventId: String,
    ): Event {
        //val snap = firestore
        //    .collection("cities")
        //    .document(cityId)
        //    .collection("events")
        //    .document(eventId)
        //    .get()
        //    .await()
        //return snap.toObject(Event::class.java) ?: throw Exception("Event not found")
        TODO("Not yet implemented")
    }

}