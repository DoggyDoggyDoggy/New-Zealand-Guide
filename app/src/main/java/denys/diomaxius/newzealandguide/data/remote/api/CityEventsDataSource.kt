package denys.diomaxius.newzealandguide.data.remote.api

import com.google.firebase.firestore.DocumentSnapshot

interface CityEventsDataSource {
    suspend fun getEvents(cityId: String, limit: Int, lastDocId: String?): List<DocumentSnapshot>
}