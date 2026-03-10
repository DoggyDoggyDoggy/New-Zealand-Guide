package denys.diomaxius.newzealandguide.ui.screen.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.screen.onboarding.components.BottomSection
import denys.diomaxius.newzealandguide.ui.screen.onboarding.data.OnboardingUiPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.FirstPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.FourthPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.pages.WeatherPage
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current
    val pages = viewModel.pages
    val isRainy = viewModel.isRainy

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 0 && isRainy) {
            viewModel.setRainyState(false)
        }
    }

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
            val pageOffset =
                ((pagerState.currentPage - position) + pagerState.currentPageOffsetFraction)
            val uiPage = pages[position]

            Box(
                modifier = Modifier.graphicsLayer {
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                    )
                    val scale = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale
                }
            ) {
                when (uiPage) {
                    is OnboardingUiPage.Welcome -> {
                        FirstPage(
                            page = uiPage.content,
                            offset = pageOffset
                        )
                    }

                    is OnboardingUiPage.Weather -> {
                        WeatherPage(
                            page = uiPage,
                            isRainy = isRainy,
                            offset = pageOffset
                        )
                    }

                    is OnboardingUiPage.Last -> {
                        FourthPage(
                            page = uiPage.content,
                            offset = pageOffset
                        )
                    }
                }
            }
        }

        BottomSection(
            pageSize = pages.size,
            currentPage = pagerState.currentPage,
            onNextClick = {
                scope.launch {
                    when {
                        pagerState.currentPage == 0 -> {
                            pagerState.animateScrollToPage(
                                1,
                                animationSpec = tween(
                                    durationMillis = 750,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }

                        pagerState.currentPage == 1 && !isRainy -> {
                            viewModel.setRainyState(true)
                        }

                        pagerState.currentPage == 1 && isRainy -> {
                            pagerState.animateScrollToPage(
                                2,
                                animationSpec = tween(
                                    durationMillis = 750,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }
                        else -> {
                            viewModel.saveOnboardingStatus()
                            navHostController.navigate(NavScreen.Home.route) {
                                popUpTo(NavScreen.Onboarding.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            }
        )
    }
}