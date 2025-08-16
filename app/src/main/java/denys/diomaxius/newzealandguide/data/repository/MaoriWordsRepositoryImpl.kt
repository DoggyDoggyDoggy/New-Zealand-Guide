package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import denys.diomaxius.newzealandguide.data.mapper.maoriwords.toDomain
import denys.diomaxius.newzealandguide.data.model.maoriwords.MaoriWordsEntity
import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import kotlinx.coroutines.tasks.await

class MaoriWordsRepositoryImpl(
    private val firestore: FirebaseFirestore
) : MaoriWordsRepository {
    override suspend fun getMaoriWords(): MaoriWords {
        val snap = firestore
            .collection("maoriWords")
            .get()
            .await()
        return snap.documents.first()
            .toObject(MaoriWordsEntity::class.java)?.toDomain()
            ?: throw Exception("Maori words data not found")
    }
}