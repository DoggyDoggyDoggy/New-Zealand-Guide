package denys.diomaxius.newzealandguide.data.remote.datasource

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CityEventsDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CityEventsDataSource {

    override suspend fun getEvents(
        cityId: String,
        limit: Int,
        lastDocId: String?
    ): List<CityEventDto> {
        var query = firestore.collection("cities")
            .document(cityId)
            .collection("events")
            .orderBy(FieldPath.documentId())
            .limit(limit.toLong())

        if (lastDocId != null) {
            query = query.startAfter(lastDocId)
        }

        return query.get().await().documents as List<CityEventDto>
    }
}