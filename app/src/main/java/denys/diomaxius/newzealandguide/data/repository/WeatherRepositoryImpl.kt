package denys.diomaxius.newzealandguide.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.data.remote.mapper.toEntity
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl (
    private val firestore: FirebaseFirestore,
    private val dao: CityDao
) : WeatherRepository {
    private data class ForecastDocument(
        val entries: List<CityWeatherDto> = emptyList(),
    )

    override suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather> {

        val cacheInfo = withContext(Dispatchers.IO) {
            dao.getWeatherCacheInfo(cityId)
        }

        if (cacheInfo == null) {
            Log.d("WeatherRepositoryImpl", "cacheInfo: $cacheInfo")
        }

        val snap = firestore
            .collection("cities")
            .document(cityId)
            .collection("weather")
            .document("forecast")
            .get()
            .await()

        val forecast = snap.toObject(ForecastDocument::class.java)
            ?.entries
            ?: throw Exception("Weather not found")

        return forecast
            .map { dto -> dto.toEntity(cityId) }
            .map(CityWeatherEntity::toDomain)
    }
}