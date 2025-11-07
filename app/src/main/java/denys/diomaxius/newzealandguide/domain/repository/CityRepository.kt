package denys.diomaxius.newzealandguide.domain.repository

import androidx.paging.PagingData
import denys.diomaxius.newzealandguide.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun citiesPagerFlow(pageSize: Int): Flow<PagingData<City>>
    suspend fun getCityById(cityId: String): City
}