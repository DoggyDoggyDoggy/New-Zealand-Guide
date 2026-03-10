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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.screen.onboarding.components.BottomSection
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.FirstPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.SecondPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.ThirdPage
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = hiltViewModel()
) {
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
                    viewModel.saveOnboardingStatus()
                    navHostController.navigate(NavScreen.Home.route) {
                        popUpTo(NavScreen.Onboarding.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}

@Composable
fun PagerContent(page: OnboardingPage) {
    when (page) {
        is OnboardingPage.First ->FirstPage(page)
        is OnboardingPage.Second -> SecondPage(page)
        is OnboardingPage.Third -> ThirdPage(page)
    }
}