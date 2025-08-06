package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.home.toDomain
import denys.diomaxius.newzealandguide.data.model.home.HomeEntity
import denys.diomaxius.newzealandguide.domain.model.home.Home
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository
import kotlinx.coroutines.tasks.await

class HomeRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : HomeRepository {
    override suspend fun getHomeData(): Home {
        val snap = firestore
            .collection("homeScreen")
            .get()
            .await()
        return snap.documents.first()
            .toObject(HomeEntity::class.java)?.toDomain()
            ?: throw Exception("Home data not found")
    }
}