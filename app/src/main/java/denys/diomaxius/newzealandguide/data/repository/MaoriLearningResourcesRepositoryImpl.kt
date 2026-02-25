package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriLearningResources
import denys.diomaxius.newzealandguide.domain.repository.MaoriLearningResourcesRepository

class MaoriLearningResourcesRepositoryImpl : MaoriLearningResourcesRepository {
    private val maoriLearningResources = listOf(
        MaoriLearningResources(
            title = "Te Aka Māori Dictionary",
            description = "Comprehensive Māori–English dictionary with pronunciation, examples, and detailed word explanations",
            url = "https://maoridictionary.co.nz/"
        ),
        MaoriLearningResources(
            title = "Māori Language Learning",
            description = "Free online lessons to help you learn basic te reo Māori, including greetings, pronunciation, and everyday phrases.",
            url = "https://www.maorilanguage.net/"
        )
    )

    override fun getMaoriLearningResources(): List<MaoriLearningResources> = maoriLearningResources
}