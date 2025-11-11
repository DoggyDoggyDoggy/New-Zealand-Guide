package denys.diomaxius.newzealandguide.domain.usecase.cityhistory

import denys.diomaxius.newzealandguide.domain.repository.CityHistoryRepository
import javax.inject.Inject

class getCityHistoryByCityIdUseCase @Inject constructor(
    private val cityHistoryRepository: CityHistoryRepository
) {
    suspend operator fun invoke(cityId: String) =
        cityHistoryRepository.getCityHistoryByCityId(cityId)
}