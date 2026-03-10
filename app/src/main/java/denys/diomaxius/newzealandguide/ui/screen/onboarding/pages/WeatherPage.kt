package denys.diomaxius.newzealandguide.ui.screen.onboarding.pages

import androidx.compose.runtime.Composable
import denys.diomaxius.newzealandguide.ui.screen.onboarding.data.OnboardingUiPage

@Composable
fun WeatherPage(
    offset: Float,
    page: OnboardingUiPage.Weather,
    isRainy: Boolean,
) {
    BasicLayout(
        page = if (isRainy) page.second else page.first,
        offset = offset
    )
}