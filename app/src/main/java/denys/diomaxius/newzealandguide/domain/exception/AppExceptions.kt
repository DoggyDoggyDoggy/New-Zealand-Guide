package denys.diomaxius.newzealandguide.domain.exception

class NoInternetException : Exception("Check your connection")
class MissingServerDataException(message: String) : Exception(message)
class NoDataAvailableException : Exception("No data on server and no local cache")