package denys.diomaxius.newzealandguide.domain.usecase

import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class GetCityHistoryById @Inject constructor(
    private val cityRepository: CityRepository,
) {
    suspend operator fun invoke(cityId: String): CityHistory =
        cityRepository.getCityHistoryById(cityId)
}