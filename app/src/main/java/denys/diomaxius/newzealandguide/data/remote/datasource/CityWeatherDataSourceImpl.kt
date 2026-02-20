package denys.diomaxius.newzealandguide.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
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
            .get(Source.SERVER)
            .await()

        return snap.toObject(ForecastDocument::class.java)
            ?.entries ?: emptyList()
    }
}