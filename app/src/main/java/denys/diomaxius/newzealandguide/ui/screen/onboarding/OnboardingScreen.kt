package denys.diomaxius.newzealandguide.ui.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen() {
    val navHostController = LocalNavController.current

    val pages = listOf(OnboardingPage.First, OnboardingPage.Second, OnboardingPage.Third)
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0x1EE0F7FA), Color(0xC987CEEB))
                )
            )
            .safeDrawingPadding()
            .padding(horizontal = 16.dp),
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically
        ) { position ->
            PagerContent(page = pages[position])
        }

        BottomSection(
            pageSize = pages.size,
            currentPage = pagerState.currentPage,
            onNextClick = {
                if (pagerState.currentPage < pages.size - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    navHostController.navigate("home_screen") {
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}