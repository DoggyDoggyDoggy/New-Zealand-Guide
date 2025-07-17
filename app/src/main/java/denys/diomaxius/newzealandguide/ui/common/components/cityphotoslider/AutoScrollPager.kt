package denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider

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
        var goingForward = true
        while (true) {
            delay(intervalMs)
            val next = when {
                goingForward && pagerState.currentPage < items.lastIndex -> pagerState.currentPage + 1
                !goingForward && pagerState.currentPage > 0 -> pagerState.currentPage - 1
                else -> {
                    goingForward = !goingForward
                    pagerState.currentPage + if (goingForward) 1 else -1
                }
            }
            pagerState.animateScrollToPage(
                page = next,
                animationSpec = tween(
                    durationMillis = animationMillis,
                    easing = FastOutSlowInEasing
                )
            )
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