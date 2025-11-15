package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.mapper.toDomain
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import kotlinx.coroutines.tasks.await

class WeatherRepositoryImpl (
    private val firestore: FirebaseFirestore
) : WeatherRepository {
    private data class ForecastDocument(
        val entries: List<CityWeatherEntity> = emptyList(),
    )

    override suspend fun getWeatherByCityId(cityId: String): List<CityWeather> {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("weather")
            .document("forecast")
            .get()
            .await()

        return snap.toObject(ForecastDocument::class.java)
            ?.entries
            ?.map(CityWeatherEntity::toDomain)
            ?: throw Exception("Weather not found")
    }
}