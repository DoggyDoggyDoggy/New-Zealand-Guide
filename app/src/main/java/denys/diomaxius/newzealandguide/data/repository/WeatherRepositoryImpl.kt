package denys.diomaxius.newzealandguide.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import kotlinx.coroutines.tasks.await

class WeatherRepositoryImpl (
    private val firestore: FirebaseFirestore
) : WeatherRepository {
    private data class ForecastDocument(
        val entries: List<CityWeatherDto> = emptyList(),
    )

    override suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather> {
        val snap = firestore
            .collection("cities")
            .document("Auckland")
            .collection("weather")
            .document("forecast")
            .get()
            .await()

        Log.d("WeatherRepositoryImpl", "getCityWeatherByCityId: ${snap?.data?.entries}")

        return snap.toObject(ForecastDocument::class.java)
            ?.entries
            ?.map(CityWeatherDto::toEntity)
            ?.map(CityWeatherEntity::toDomain)
            ?: throw Exception("Weather not found")
    }
}