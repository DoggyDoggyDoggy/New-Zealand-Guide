package denys.diomaxius.newzealandguide.domain.repository

interface AnalyticsHelper {
    fun logEvent(name: String, params: Map<String, Any>? = null)
    fun logScreenView(screenName: String)
}