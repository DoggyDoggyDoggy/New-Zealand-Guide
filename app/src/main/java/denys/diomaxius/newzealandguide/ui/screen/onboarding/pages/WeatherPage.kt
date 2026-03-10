package denys.diomaxius.newzealandguide.ui.screen.onboarding.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import denys.diomaxius.newzealandguide.ui.screen.onboarding.data.OnboardingUiPage

@Composable
fun WeatherPage(
    offset: Float,
    page: OnboardingUiPage.Weather,
    isRainy: Boolean,
) {
    AnimatedContent(
        targetState = isRainy,
        transitionSpec = {
            fadeIn(animationSpec = tween(1200)) togetherWith fadeOut(animationSpec = tween(700))
        },
        label = "WeatherFadeTransition"
    ) { targetIsRainy ->
        BasicLayout(
            page = if (targetIsRainy) page.second else page.first,
            offset = offset
        )
    }
}