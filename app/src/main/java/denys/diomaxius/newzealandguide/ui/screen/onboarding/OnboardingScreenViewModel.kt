package denys.diomaxius.newzealandguide.ui.screen.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.onboarding.SaveOnboardingStatusUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.ui.screen.onboarding.data.OnboardingUiPage
import denys.diomaxius.newzealandguide.ui.screen.onboarding.data.PageContent

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    private val saveOnboardingStatusUseCase: SaveOnboardingStatusUseCase
) : ViewModel() {
    val pages = listOf(
        OnboardingUiPage.Welcome(
            content = PageContent(
                image = R.drawable.kea_stand,
                title = "Kia Ora! Welcome to Aotearoa!",
                description = "Your journey through the Land of the Long White Cloud starts here. Let Kea guide you to the heart of New Zealand’s most iconic cities."
            )
        ),
        OnboardingUiPage.Weather(
            first = PageContent(
                image = R.drawable.kea_sunny_weather,
                title = "Stay ahead of the weather",
                description = "Get curated city guides, daily weather forecasts, and the latest events every week. Curate your favorites, share them with friends, and never miss a beat!"
            ),
            second = PageContent(
                image = R.drawable.kea_rainy_weather,
                title = "3 PM: Rain or Shine?",
                description = "Get a daily weather update for the most active hour of the day. It’s a great starting point for your plans, but in Aotearoa, it’s always wise to be ready for a sudden afternoon shower!"
            )
        ),
        OnboardingUiPage.Last(
            content = PageContent(
                image = R.drawable.kea_fly,
                title = "Explore offline, anytime",
                description = "All city stories and must-visit spots are already packed into your phone! Just hop online for a quick second to sync the latest weather and events, and they’ll be ready for you to use anytime, anywhere."
            )
        )
    )

    var isRainy by mutableStateOf(false)
        private set

    fun setRainyState(rainy: Boolean) {
        isRainy = rainy
    }

    fun saveOnboardingStatus() = viewModelScope.launch {
        saveOnboardingStatusUseCase()
    }
}