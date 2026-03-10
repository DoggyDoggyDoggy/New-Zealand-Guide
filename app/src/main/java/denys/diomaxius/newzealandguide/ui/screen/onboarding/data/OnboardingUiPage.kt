package denys.diomaxius.newzealandguide.ui.screen.onboarding.data

sealed class OnboardingUiPage {
    data class Welcome(val content: PageContent) : OnboardingUiPage()

    data class Weather(
        val first: PageContent,
        val second: PageContent
    ) : OnboardingUiPage()

    data class Last(val content: PageContent) : OnboardingUiPage()
}