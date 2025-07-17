package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.toDomain
import denys.diomaxius.newzealandguide.data.model.CityEntity
import denys.diomaxius.newzealandguide.data.model.CityPlaceTopicEntity
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityPlaceTopic
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import kotlinx.coroutines.tasks.await

class CityRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : CityRepository {
    override suspend fun getAllCities(): List<City> {
        val snap = firestore
            .collection("cities")
            .get()
            .await()

        val entities = snap.documents.mapNotNull { doc ->
            doc.toObject(CityEntity::class.java)
                ?.copy(id = doc.id)
        }

        return entities.map { it.toDomain() }
    }

    override suspend fun getPlacesByCityId(cityId: String): List<CityPlaceTopic> {
        val snap  = firestore
            .collection("cities")
            .document(cityId.toString())
            .collection("places")
            .get()
            .await()

        return snap.documents.mapNotNull { doc ->
            doc.toObject(CityPlaceTopicEntity::class.java)
                ?.toDomain()
        }
    }

    override fun getCityById(id: String): City {
        TODO("Not yet implemented")
    }
}