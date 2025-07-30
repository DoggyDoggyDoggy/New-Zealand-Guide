package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
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
                imageUrl = doc.getImageUrl()
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
            sessions = emptyList(),
            imageUrl = snap.getImageUrl()
        ).toDomain()
    }
}

fun DocumentSnapshot.getImageUrl(): String {
    val imagesList = (get("images") as? Map<*, *>)?.get("images") as? List<*>
    val first = imagesList?.firstOrNull() as? Map<*, *>
    val transforms = first?.get("transforms") as? Map<*, *>
    val tList = transforms?.get("transforms") as? List<*>
    return (tList?.getOrNull(2) as? Map<*, *>)?.get("url") as? String ?: ""
}
