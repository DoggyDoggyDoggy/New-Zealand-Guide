package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCitiesFlowUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(favoriteCities: Boolean = false): Flow<List<City>> {
        return if (favoriteCities) {
            cityRepository.getAllFavoriteCitiesFlow()
        } else {
            cityRepository.getAllCitiesFlow()
        }
    }
}