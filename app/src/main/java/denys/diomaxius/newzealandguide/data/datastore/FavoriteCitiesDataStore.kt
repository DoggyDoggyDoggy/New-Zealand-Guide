package denys.diomaxius.newzealandguide.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val FAVORITE_CITIES_PREFERENCES_NAME = "favorite_cities_prefs"
private val Context.cityIdsDataStore by preferencesDataStore(name = FAVORITE_CITIES_PREFERENCES_NAME)

class FavoriteCitiesDataStore(private val context: Context) {
    companion object {
        private val FAVORITE_CITY_IDS_KEY = stringSetPreferencesKey("favorite_city_ids")
    }

    val favoriteCityIds: Flow<List<String>> = context.cityIdsDataStore.data
        .map { prefs -> prefs[FAVORITE_CITY_IDS_KEY]?.toList() ?: emptyList() }

    suspend fun addCityId(id: String) {
        context.cityIdsDataStore.edit { prefs ->
            val current = prefs[FAVORITE_CITY_IDS_KEY]?.toMutableSet() ?: mutableSetOf()
            current.add(id)
            prefs[FAVORITE_CITY_IDS_KEY] = current
        }
    }

    suspend fun removeCityId(id: String) {
        context.cityIdsDataStore.edit { prefs ->
            val current = prefs[FAVORITE_CITY_IDS_KEY]?.toMutableSet() ?: mutableSetOf()
            current.remove(id)
            prefs[FAVORITE_CITY_IDS_KEY] = current
        }
    }
}