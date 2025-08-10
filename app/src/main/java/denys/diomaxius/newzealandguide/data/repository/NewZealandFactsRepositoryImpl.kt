package denys.diomaxius.newzealandguide.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.newzealandguide.data.mapper.nzfacts.toDomain
import denys.diomaxius.newzealandguide.data.model.nzfacts.NewZealandFactsEntity
import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import kotlinx.coroutines.tasks.await

class NewZealandFactsRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : NewZealandFactsRepository  {
    override suspend fun getNewZealandFacts(): NewZealandFacts {
        val snap = firestore
            .collection("nzFacts")
            .get()
            .await()
        return snap.documents.first()
            .toObject(NewZealandFactsEntity::class.java)?.toDomain()
            ?: throw Exception("New Zealand facts data not found")
    }
}