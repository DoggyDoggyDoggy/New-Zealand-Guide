package denys.diomaxius.newzealandguide.data.remote.datasource

import android.util.Log
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto
import kotlinx.coroutines.tasks.await
import java.time.Instant
import javax.inject.Inject

class CityEventsDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CityEventsDataSource {

    override suspend fun getEvents(
        cityId: String,
        limit: Int,
        lastDocId: String?
    ): List<CityEventDto> {

        Log.d("CityEventsDataSourceImpl", "Get events. CityId: $cityId")

        var query = firestore.collection("cities")
            .document(cityId)
            .collection("events")
            .orderBy(FieldPath.documentId())
            .limit(limit.toLong())

        Log.d("CityEventsDataSourceImpl", "Query: $query")

        if (lastDocId != null) {
            query = query.startAfter(lastDocId)
        }

        return query.get(Source.SERVER).await().mapNotNull{
            it.toObject(CityEventDto::class.java)
        }
    }

    override suspend fun fetchLastUpdatedAt(cityId: String): Instant? {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("lastUpdate")
            .document("events")
            .get(Source.SERVER)
            .await()

        val timestamp = snap.getTimestamp("updatedAt")

        return timestamp?.let {
            Instant.ofEpochSecond(it.seconds, it.nanoseconds.toLong())
        }
    }
}