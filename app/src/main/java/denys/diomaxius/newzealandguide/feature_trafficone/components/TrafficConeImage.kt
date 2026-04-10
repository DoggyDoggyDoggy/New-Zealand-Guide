package denys.diomaxius.newzealandguide.feature_trafficone.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.get
import denys.diomaxius.newzealandguide.feature_trafficone.TrafficConeState

@Composable
fun TrafficConeImage(
    state: TrafficConeState,
    androidBitmap: Bitmap,
    painter: Painter,
    imgAspectRatio: Float
) {
    Image(
        modifier = Modifier
            .padding(top = 16.dp)
            .height(325.dp)
            .aspectRatio(imgAspectRatio)
            .scale(state.scale.value)
            .pixelPerfectClickable(androidBitmap) { state.triggerHit() },
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        colorFilter = if (state.flashIntensity.value > 0f) {
            ColorFilter.tint(
                Color.Red.copy(alpha = state.flashIntensity.value),
                BlendMode.SrcAtop
            )
        } else null
    )
}

fun Modifier.pixelPerfectClickable(
    androidBitmap: Bitmap,
    onClick: () -> Unit,
): Modifier = this.pointerInput(androidBitmap) {
    detectTapGestures { offset ->
        // Calculate the scale between the View size and the Bitmap size
        val scaleX = androidBitmap.width.toFloat() / size.width
        val scaleY = androidBitmap.height.toFloat() / size.height

        val pixelX = (offset.x * scaleX).toInt().coerceIn(0, androidBitmap.width - 1)
        val pixelY = (offset.y * scaleY).toInt().coerceIn(0, androidBitmap.height - 1)

        // Take out a pixel and check Alpha
        val pixel = androidBitmap[pixelX, pixelY]
        val alpha = (pixel shr 24) and 0xFF

        if (alpha > 10) {
            onClick()
        }
    }
}