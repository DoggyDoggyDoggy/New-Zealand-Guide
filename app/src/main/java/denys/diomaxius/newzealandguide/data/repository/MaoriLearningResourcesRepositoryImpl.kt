package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriLearningResources
import denys.diomaxius.newzealandguide.domain.repository.MaoriLearningResourcesRepository

class MaoriLearningResourcesRepositoryImpl : MaoriLearningResourcesRepository {
    private val maoriLearningResources = listOf(
        MaoriLearningResources(
            title = "",
            description = "",
            url = ""
        )
    )

    override fun getMaoriLearningResources(): List<MaoriLearningResources> = maoriLearningResources
}