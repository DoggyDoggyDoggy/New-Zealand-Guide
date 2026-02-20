package denys.diomaxius.newzealandguide.domain.exception

class NoInternetException : Exception("Check your connection")
class MissingServerDataException(message: String) : Exception(message)