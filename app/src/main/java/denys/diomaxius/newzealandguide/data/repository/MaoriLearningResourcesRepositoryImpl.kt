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
            title = "Te Whanake",
            description = "Structured Māori language learning programme with textbooks, online exercises, audio resources, and interactive activities",
            url = "https://tewhanake.maori.nz/"
        ),
        MaoriLearningResources(
            title = "Māori Language Learning",
            description = "Free online lessons to help you learn basic te reo Māori, including greetings, pronunciation, and everyday phrases",
            url = "https://www.maorilanguage.net/"
        ),
        MaoriLearningResources(
            title = "Te Taura Whiri i te Reo Māori",
            description = "Official Māori Language Commission with resources, guidance, and initiatives to support learning and revitalising te reo Māori",
            url = "https://www.tetaurawhiri.govt.nz/"
        ),
        MaoriLearningResources(
            title = "Kupu Māori",
            description = "Get a new Māori word every day to grow your vocabulary and strengthen your grammar skills",
            url = "https://kupu.maori.nz/"
        )
    )

    override fun getMaoriLearningResources(): List<MaoriLearningResources> = maoriLearningResources
}