package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.repository.FavoriteCitiesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetFavoriteCityIdsUseCase @Inject constructor(
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
    private val getAllCitiesUseCase: GetAllCitiesUseCase
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<City>> =
        favoriteCitiesRepository.getFavoriteCityIds()
            .mapLatest { ids ->
                val all = getAllCitiesUseCase()
                all.filter { it.id in ids }.sortedBy { it.name }
            }
}