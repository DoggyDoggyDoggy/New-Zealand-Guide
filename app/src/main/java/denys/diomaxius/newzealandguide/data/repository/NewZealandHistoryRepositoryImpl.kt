package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.mapper.nzhistory.toDomain
import denys.diomaxius.newzealandguide.data.model.nzhistory.NewZealandHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import kotlinx.coroutines.tasks.await

class NewZealandHistoryRepositoryImpl(
    private val firestore: FirebaseFirestore
) : NewZealandHistoryRepository {
    override suspend fun getNewZealandHistory(): NewZealandHistory {
        val snap = firestore
            .collection("nzHistory")
            .get()
            .await()
        return snap.documents.first()
            .toObject(NewZealandHistoryEntity::class.java)?.toDomain()
            ?: throw Exception("Maori words data not found")
    }
}