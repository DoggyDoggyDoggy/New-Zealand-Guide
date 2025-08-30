package denys.diomaxius.newzealandguide.domain.usecase.city

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityWithFavorite
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.usecase.city.favorite.GetFavoriteCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase
) {
    operator fun invoke(): Flow<List<CityWithFavorite>> {
        val citiesFlow: Flow<List<City>> =
            flow {
                val cities = cityRepository.getAllCities().sortedBy { it.name }
                emit(cities)
            }.flowOn(Dispatchers.IO)

        return combine(
            citiesFlow,
            getFavoriteCitiesUseCase()
        ) { cities, favIds ->
            val favSet = favIds.toSet()
            cities.map { city ->
                CityWithFavorite(city = city, isFavorite = favSet.contains(city.id))
            }
        }
    }
}