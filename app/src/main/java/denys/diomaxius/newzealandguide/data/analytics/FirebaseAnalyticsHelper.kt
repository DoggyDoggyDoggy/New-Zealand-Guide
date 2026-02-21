package denys.diomaxius.newzealandguide.data.analytics

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper

class FirebaseAnalyticsHelper(context: Context) : AnalyticsHelper {
    private val firebaseAnalytics = Firebase.analytics

    override fun logEvent(name: String, params: Map<String, Any>?) {
        firebaseAnalytics.logEvent(name) {
            params?.forEach { (key, value) ->
                when (value) {
                    is String -> param(key, value)
                    is Long -> param(key, value)
                    is Double -> param(key, value)
                    is Int -> param(key, value.toLong())
                }
            }
        }
    }

    override fun logScreenView(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
        }
    }
}