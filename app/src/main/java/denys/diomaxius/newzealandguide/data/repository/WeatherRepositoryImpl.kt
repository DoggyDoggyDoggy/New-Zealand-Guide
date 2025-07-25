package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.weather.toDomain
import denys.diomaxius.newzealandguide.data.model.weather.WeatherEntity
import denys.diomaxius.newzealandguide.data.model.weather.WeatherIconEntity
import denys.diomaxius.newzealandguide.domain.model.weather.Weather
import denys.diomaxius.newzealandguide.domain.model.weather.WeatherIcon
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import kotlinx.coroutines.tasks.await


class WeatherRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore,
) : WeatherRepository {
    private data class ForecastDocument(
        val entries: List<WeatherEntity> = emptyList(),
    )

    override suspend fun getWeatherByCityId(cityId: String): List<Weather> {
        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("weather")
            .document("forecast")
            .get()
            .await()

        return snap.toObject(ForecastDocument::class.java)
            ?.entries
            ?.map(WeatherEntity::toDomain)
            ?: throw Exception("Weather not found")
    }

    override suspend fun getWeatherIcons(): List<WeatherIcon> {
        val snap = firestore
            .collection("weatherIcons")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(WeatherIconEntity::class.java)?.toDomain()
        }
    }
}