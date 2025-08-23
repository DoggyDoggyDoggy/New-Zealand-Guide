package denys.diomaxius.newzealandguide.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteCitiesRepository {
    fun getFavoriteCityIds(): Flow<List<String>>
    suspend fun addFavoriteCityId(id: String)
    suspend fun removeFavoriteCityId(id: String)
}