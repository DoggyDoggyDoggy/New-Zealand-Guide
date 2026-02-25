package denys.diomaxius.newzealandguide.domain.usecase.maorihub

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriLearningResources
import denys.diomaxius.newzealandguide.domain.repository.MaoriLearningResourcesRepository
import javax.inject.Inject

class GetMaoriLearningResourcesUseCase @Inject constructor(
    private val maoriLearningResourcesRepository: MaoriLearningResourcesRepository
) {
    operator fun invoke(): List<MaoriLearningResources> =
        maoriLearningResourcesRepository.getMaoriLearningResources()
}