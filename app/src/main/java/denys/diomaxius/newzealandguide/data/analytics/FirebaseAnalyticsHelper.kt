package denys.diomaxius.newzealandguide.data.analytics

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper

class FirebaseAnalyticsHelper(private val context: Context? = null) : AnalyticsHelper {
    private val firebaseAnalytics = Firebase.analytics

    override fun logEvent(name: String, params: Map<String, Any>?) {
        if (name.isBlank()) return

        firebaseAnalytics.logEvent(name) {
            params?.forEach { (key, valueRaw) ->
                val keySanitized = key
                    .lowercase()
                    .replace(Regex("[^a-z0-9_]"), "_")
                    .take(40)

                when (valueRaw) {
                    is String -> param(keySanitized, valueRaw.take(100))
                    is Long -> param(keySanitized, valueRaw)
                    is Int -> param(keySanitized, valueRaw.toLong())
                    is Double -> param(keySanitized, valueRaw)
                    is Float -> param(keySanitized, valueRaw.toDouble())
                    is Boolean -> param(keySanitized, if (valueRaw) 1L else 0L)
                    else -> {
                        // ignore unsupported types
                    }
                }
            }
        }
    }

    override fun logScreenView(screenName: String) {
        if (screenName.isBlank()) return
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName.take(100))
        }
    }
}