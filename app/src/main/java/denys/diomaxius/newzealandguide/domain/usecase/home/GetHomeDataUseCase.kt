package denys.diomaxius.newzealandguide.domain.usecase.home

import denys.diomaxius.newzealandguide.domain.model.Home
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
   operator fun invoke(): Home = homeRepository.getHomeData()
}