package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.nzhistory.toDomain
import denys.diomaxius.newzealandguide.data.model.nzhistory.NewZealandHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import kotlinx.coroutines.tasks.await

class NewZealandHistoryRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : NewZealandHistoryRepository {
    override suspend fun getNewZealandHistory(): NewZealandHistory {
        val snap = firestore
            .collection("nzHistory")
            .get()
            .await()
        return snap.documents.first()
            .toObject(NewZealandHistoryEntity::class.java)?.toDomain()
            ?: throw Exception("New Zealand history data not found")
    }
}