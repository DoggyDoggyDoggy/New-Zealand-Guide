package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import javax.inject.Inject

class ToggleEventFavoriteUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(cityId: String, eventId: String ) =
        cityRepository.toggleEventFavorite(cityId, eventId)
}