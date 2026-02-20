package denys.diomaxius.newzealandguide.domain.repository

interface ErrorLogger {
    fun logException(throwable: Throwable, map: Map<String, String>? = null)
    fun logMessage(message: String)
}