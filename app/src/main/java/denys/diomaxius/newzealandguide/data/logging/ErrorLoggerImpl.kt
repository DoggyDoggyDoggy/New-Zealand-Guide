package denys.diomaxius.newzealandguide.data.logging

import com.google.firebase.crashlytics.FirebaseCrashlytics
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger

class ErrorLoggerImpl : ErrorLogger {
    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun logException(
        throwable: Throwable,
        map: Map<String, String>?,
    ) {
        map?.forEach { (key, value) ->
            crashlytics.setCustomKey(key, value)
        }
        crashlytics.recordException(throwable)
    }

    override fun logMessage(message: String) {
        crashlytics.log(message)
    }
}