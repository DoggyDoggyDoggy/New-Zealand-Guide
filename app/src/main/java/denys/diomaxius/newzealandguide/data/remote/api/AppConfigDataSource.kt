package denys.diomaxius.newzealandguide.data.remote.api

interface AppConfigDataSource {
    suspend fun getWeatherUpdateTag(): String
    suspend fun getEventsUpdateTag(): String
}