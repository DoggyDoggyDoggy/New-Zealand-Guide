package denys.diomaxius.newzealandguide.feature_trafficone.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PhraseMessage(visible: Boolean, phrase: String) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        modifier = Modifier.offset(y = (-40).dp)
    ) {
        Text(
            text = phrase,
            modifier = Modifier
                .background(Color.Black.copy(0.7f), RoundedCornerShape(8.dp))
                .padding(8.dp),
            color = Color.White
        )
    }
}