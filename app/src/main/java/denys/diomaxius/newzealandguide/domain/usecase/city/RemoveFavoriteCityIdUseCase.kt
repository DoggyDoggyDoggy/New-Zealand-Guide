package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.repository.FavoriteCitiesRepository
import javax.inject.Inject

class RemoveFavoriteCityIdUseCase @Inject constructor(
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
) {
    suspend operator fun invoke(id: String) =
        favoriteCitiesRepository.removeFavoriteCityId(id)
}