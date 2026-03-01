package denys.diomaxius.newzealandguide.data.remote.datasource

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import denys.diomaxius.newzealandguide.data.remote.api.AppConfigDataSource
import kotlinx.coroutines.tasks.await

class AppConfigDataSourceImpl (
    private val remoteConfig: FirebaseRemoteConfig
): AppConfigDataSource {
    override suspend fun getWeatherUpdateTag(): String {
        return try {
            remoteConfig.fetchAndActivate().await()
            val weatherTag = remoteConfig.getString("weather_update_tag")
            weatherTag
        } catch (e: Exception) {
            // If there is no internet, it will return the cached value or the default one
            remoteConfig.getString("weather_update_tag")
        }
    }

    override suspend fun getEventsUpdateTag(): String {
        return try {
            remoteConfig.fetchAndActivate().await()
            remoteConfig.getString("events_update_tag")
        } catch (e: Exception) {
            remoteConfig.getString("events_update_tag")
        }
    }
}