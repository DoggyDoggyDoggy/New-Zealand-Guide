package denys.diomaxius.newzealandguide.ui.screen.onboarding

import denys.diomaxius.newzealandguide.R

sealed class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnboardingPage(
        image = R.drawable.kea_stand,
        title = "Kia Ora! Welcome to Aotearoa!",
        description = "Your journey through the Land of the Long White Cloud starts here. Let Kea guide you to the heart of New Zealand’s most iconic cities."
    )
    object Second : OnboardingPage(
        image = R.drawable.kea_sunny_weather,
        title = "Stay ahead of the weather",
        description = "Get curated city guides, daily weather forecasts, and the latest events every week. Curate your favorites, share them with friends, and never miss a beat!"
    )
    object Third : OnboardingPage(
        image = R.drawable.kea_fly,
        title = "Explore offline, anytime",
        description = "All city stories and must-visit spots are already packed into your phone! Just hop online for a quick second to sync the latest weather and events, and they’ll be ready for you to use anytime, anywhere."
    )
}