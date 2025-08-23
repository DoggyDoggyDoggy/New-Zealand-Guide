package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.data.datastore.FavoriteCitiesDataStore
import denys.diomaxius.newzealandguide.domain.repository.FavoriteCitiesRepository

class FavoriteCitiesRepositoryImpl(
    private val dataStore: FavoriteCitiesDataStore
) : FavoriteCitiesRepository {
    override fun getFavoriteCityIds() = dataStore.favoriteCityIds
    override suspend fun addFavoriteCityId(id: String) = dataStore.addCityId(id)
    override suspend fun removeFavoriteCityId(id: String) = dataStore.removeCityId(id)
}