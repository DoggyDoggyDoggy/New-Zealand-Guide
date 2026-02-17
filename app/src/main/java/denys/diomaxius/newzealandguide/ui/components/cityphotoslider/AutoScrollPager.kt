package denys.diomaxius.newzealandguide.ui.components.cityphotoslider

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun AutoScrollPager(
    items: List<String>,
    modifier: Modifier = Modifier,
    intervalMs: Long = 4000L,
    animationMillis: Int = 1000,
    pageContent: @Composable (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { items.size })

    LaunchedEffect(pagerState) {
        pagerState.scrollToPage(pagerState.currentPage)

        var goingForward = true
        while (true) {
            delay(intervalMs)

            if (!pagerState.isScrollInProgress) {
                val next = when {
                    goingForward && pagerState.currentPage < items.lastIndex -> pagerState.currentPage + 1
                    !goingForward && pagerState.currentPage > 0 -> pagerState.currentPage - 1
                    else -> {
                        goingForward = !goingForward
                        if (goingForward) pagerState.currentPage + 1 else pagerState.currentPage - 1
                    }
                }

                val targetPage = next.coerceIn(0, items.size - 1)

                pagerState.animateScrollToPage(
                    page = targetPage,
                    animationSpec = tween(
                        durationMillis = animationMillis,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = modifier
    ) { page ->
        pageContent(items[page])
    }
}