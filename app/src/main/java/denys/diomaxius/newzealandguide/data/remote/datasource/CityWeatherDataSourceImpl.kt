package denys.diomaxius.newzealandguide.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import kotlinx.coroutines.tasks.await

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
            .get()
            .await()

        return snap.toObject(ForecastDocument::class.java)
            ?.entries
            ?: throw Exception("Weather not found")
    }
}