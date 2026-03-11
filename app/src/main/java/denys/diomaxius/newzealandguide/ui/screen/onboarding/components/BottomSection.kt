package denys.diomaxius.newzealandguide.ui.screen.onboarding.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times

@Composable
fun BottomSection(
    modifier: Modifier,
    pageSize: Int,
    currentPage: Int,
    onNextClick: () -> Unit,
) {
    val dotSize = 12.dp
    val dotSpacing = 8.dp
    val totalDotWidth = dotSize + dotSpacing

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextClick
        ) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = if (currentPage == pageSize - 1) "Let's Explore!" else "Next",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dotSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(pageSize) {
                    Box(
                        modifier = Modifier
                            .size(dotSize)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.7f))
                    )
                }
            }

            val indicatorOffset by animateDpAsState(
                targetValue = (currentPage - (pageSize - 1) / 2f) * totalDotWidth.value.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "IndicatorMovement"
            )

            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .size(dotSize + 4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF005048))
            )
        }
    }
}