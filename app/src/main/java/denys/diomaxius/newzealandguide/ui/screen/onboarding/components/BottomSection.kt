package denys.diomaxius.newzealandguide.ui.screen.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSection(
    modifier: Modifier,
    pageSize: Int,
    currentPage: Int,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = modifier
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

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageSize) { index ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (currentPage == index) 14.dp else 12.dp)
                        .clip(CircleShape)
                        .background(if (currentPage == index) Color(0xFF005048) else Color.White)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(6.dp)
        )
    }
}