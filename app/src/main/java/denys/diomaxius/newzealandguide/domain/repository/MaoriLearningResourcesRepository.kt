package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriLearningResources

interface MaoriLearningResourcesRepository {
    fun getMaoriLearningResources(): List<MaoriLearningResources>
}