package denys.diomaxius.newzealandguide.data.remote.datasource

import android.util.Log
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
            Log.d("AppConfigDataSource", "Weather update tag: $weatherTag")
            weatherTag
        } catch (e: Exception) {
            Log.d("AppConfigDataSource", "Exception: $e")
            // If there is no internet, it will return the cached value or the default one
            remoteConfig.getString("weather_update_tag")
        }
    }
}