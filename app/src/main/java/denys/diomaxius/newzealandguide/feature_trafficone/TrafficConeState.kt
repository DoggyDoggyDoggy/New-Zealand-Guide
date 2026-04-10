package denys.diomaxius.newzealandguide.feature_trafficone

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import denys.diomaxius.newzealandguide.feature_trafficone.data.nzPhrases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class TrafficConeState(
    val scope: CoroutineScope,
    val phrases: List<String>,
) {
    val scale = Animatable(1f)
    val flashIntensity = Animatable(0f)

    var currentPhrase by mutableStateOf("")
        private set
    var isPhraseVisible by mutableStateOf(false)
        private set

    private var hitJob: Job? = null
    private var phraseJob: Job? = null

    fun triggerHit() {
        hitJob?.cancel()
        hitJob = scope.launch {
            launch {
                scale.snapTo(0.85f)
                scale.animateTo(1f, tween(165))
            }
            launch {
                flashIntensity.snapTo(0.5f)
                flashIntensity.animateTo(0f, tween(75))
            }

            if (!isPhraseVisible && Random.nextFloat() < 0.33f) {
                showPhrase()
            }
        }
    }

    private fun showPhrase() {
        currentPhrase = phrases.random()
        isPhraseVisible = true

        phraseJob?.cancel()
        phraseJob = scope.launch {
            delay(2500)
            isPhraseVisible = false
        }
    }
}

@Composable
fun rememberTrafficConeState(
    phrases: List<String> = nzPhrases,
    scope: CoroutineScope = rememberCoroutineScope(),
) = remember(phrases, scope) { TrafficConeState(scope, phrases) }