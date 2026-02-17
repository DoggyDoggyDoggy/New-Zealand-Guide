package denys.diomaxius.newzealandguide.ui.screen.nzhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.shadow.Shadow as BoxShadow

@Composable
fun ParagraphBlock(
    paragraph: String,
    paragraphTitle: String,
) {
    Column(
        modifier = Modifier
            .dropShadow(
                shape = RoundedCornerShape(
                    topEnd = 32.dp,
                    bottomStart = 32.dp,
                ),
                shadow = BoxShadow(
                    radius = 4.dp,
                    spread = 1.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    offset = DpOffset(x = 0.dp, 0.dp)
                )
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(
                    topEnd = 32.dp,
                    bottomStart = 32.dp,
                )
            )

    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = paragraphTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Box(
            modifier = Modifier
                .dropShadow(
                    shape = RoundedCornerShape(
                        topEnd = 32.dp,
                        bottomStart = 32.dp,
                    ),
                    shadow = BoxShadow(
                        radius = 12.dp,
                        spread = 3.dp,
                        color = MaterialTheme.colorScheme.surfaceDim,
                        offset = DpOffset(x = 3.dp, 3.dp)
                    )
                )
                .dropShadow(
                    shape = RoundedCornerShape(
                        topEnd = 32.dp,
                        bottomStart = 32.dp,
                    ),
                    shadow = BoxShadow(
                        radius = 3.dp,
                        spread = 0.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        offset = DpOffset(x = -2.dp, -2.dp)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topEnd = 32.dp,
                            bottomStart = 32.dp,
                        )
                    )
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = paragraph,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}