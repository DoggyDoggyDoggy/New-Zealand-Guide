package denys.diomaxius.newzealandguide.feature_trafficone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.feature_trafficone.components.PhraseMessage
import denys.diomaxius.newzealandguide.feature_trafficone.components.TrafficConeImage


@Composable
fun TrafficConeScreen() {
    Scaffold {
       Content(modifier = Modifier.padding(it))
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
) {
    val resId = R.drawable.cone
    val bitmap = ImageBitmap.imageResource(id = resId)
    val androidBitmap = remember(bitmap) { bitmap.asAndroidBitmap() }
    val painter = remember(bitmap) { BitmapPainter(bitmap) }
    val imgAspectRatio = bitmap.width.toFloat() / bitmap.height.toFloat()

    val state = rememberTrafficConeState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.TopCenter) {
            TrafficConeImage(
                state = state,
                androidBitmap = androidBitmap,
                painter = painter,
                imgAspectRatio = imgAspectRatio
            )

            PhraseMessage(
                visible = state.isPhraseVisible,
                phrase = state.currentPhrase
            )
        }
    }
}