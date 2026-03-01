package denys.diomaxius.newzealandguide.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import kotlinx.coroutines.tasks.await
import java.time.Instant

class CityWeatherDataSourceImpl(
    private val firestore: FirebaseFirestore,
) : CityWeatherDataSource {
    private data class ForecastDocument(
        val entries: List<CityWeatherDto> = emptyList(),
    )

    override suspend fun fetchForecast(cityId: String): List<CityWeatherDto> {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("weather")
            .document("forecast")
            .get(Source.SERVER)
            .await()

        return snap.toObject(ForecastDocument::class.java)
            ?.entries ?: emptyList()
    }

    override suspend fun fetchLastUpdatedAt(cityId: String): Instant? {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("lastUpdate")
            .document("weather")
            .get(Source.SERVER)
            .await()

        val timestamp = snap.getTimestamp("updatedAt")

        return timestamp?.let {
            Instant.ofEpochSecond(it.seconds, it.nanoseconds.toLong())
        }
    }
}