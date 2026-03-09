package denys.diomaxius.newzealandguide.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PagerContent(page: OnboardingPage) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth(),
            painter = painterResource(id = page.image),
            contentDescription = "Kea",
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = page.title,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp,
            color = Color(0xFF005048)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = page.description,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color(0xFF334B47)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}