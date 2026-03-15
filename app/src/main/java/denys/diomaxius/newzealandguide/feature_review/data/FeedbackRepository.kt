package denys.diomaxius.newzealandguide.feature_review.data

import android.content.Context
import android.os.Build
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FeedbackRepository(context: Context) {
    private val db = FirebaseFirestore.getInstance()

    suspend fun sendComplaint(rating: Int, feedback: String): Result<Unit> {
        return try {
            val complaint = hashMapOf(
                "rating" to rating,
                "feedback" to feedback,
                "timestamp" to System.currentTimeMillis(),
                "device" to "${Build.MANUFACTURER} ${Build.MODEL}",
                "androidRelease" to Build.VERSION.RELEASE,
                "sdkVersion" to Build.VERSION.SDK_INT,
            )
            db.collection("complaints").add(complaint).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}