package denys.diomaxius.newzealandguide.feature_review.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "review_prefs")

class ReviewPreferencesManager(private val context: Context) {
    private val ACTION_COUNT_KEY = intPreferencesKey("action_count")
    private val LAST_PROMPT_TIME_KEY = longPreferencesKey("last_prompt_time")
    private val SHOW_REVIEW_DIALOG_AGAIN_KEY = booleanPreferencesKey("show_review_dialog_again")
    private val SHOW_REVIEW_DIALOG_LATER_KEY = booleanPreferencesKey("show_review_dialog_later")
    private val LAUNCH_COUNT_KEY = intPreferencesKey("launch_count")

    val reviewDataFlow: Flow<ReviewPrefs> = context.dataStore.data.map { prefs ->
        ReviewPrefs(
            count = prefs[ACTION_COUNT_KEY] ?: 0,
            lastPromptTime = prefs[LAST_PROMPT_TIME_KEY] ?: 0L,
            showAgain = prefs[SHOW_REVIEW_DIALOG_AGAIN_KEY] ?: true,
            showLater = prefs[SHOW_REVIEW_DIALOG_LATER_KEY] ?: false,
            launchCount = prefs[LAUNCH_COUNT_KEY] ?: 0
        )
    }

    suspend fun incrementActionCount() {
        context.dataStore.edit { prefs ->
            val current = prefs[ACTION_COUNT_KEY] ?: 0
            prefs[ACTION_COUNT_KEY] = current + 1
        }
    }

    suspend fun incrementLaunchCount() {
        context.dataStore.edit { prefs ->
            val current = prefs[LAUNCH_COUNT_KEY] ?: 0
            prefs[LAUNCH_COUNT_KEY] = current + 1
        }
    }

    suspend fun resetCounterAndSetTimestamp() {
        context.dataStore.edit { prefs ->
            prefs[ACTION_COUNT_KEY] = 0
            prefs[LAST_PROMPT_TIME_KEY] = System.currentTimeMillis()
            prefs[SHOW_REVIEW_DIALOG_AGAIN_KEY] = true
            prefs[SHOW_REVIEW_DIALOG_LATER_KEY] = true
            prefs[LAUNCH_COUNT_KEY] = 0
        }
    }

    suspend fun dontShowDialogAgain() {
        context.dataStore.edit { prefs ->
            prefs[SHOW_REVIEW_DIALOG_AGAIN_KEY] = false
            prefs[SHOW_REVIEW_DIALOG_LATER_KEY] = false
        }
    }

    suspend fun setShowDialogLater() {
        context.dataStore.edit { prefs ->
            prefs[SHOW_REVIEW_DIALOG_LATER_KEY] = true
        }
        resetCounterAndSetTimestamp()
    }
}

data class ReviewPrefs(
    val count: Int,
    val lastPromptTime: Long,
    val showAgain: Boolean,
    val showLater: Boolean,
    val launchCount: Int
)