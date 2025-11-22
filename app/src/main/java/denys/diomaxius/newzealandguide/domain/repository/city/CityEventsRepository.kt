package denys.diomaxius.newzealandguide.domain.repository.city

import androidx.paging.PagingData
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import kotlinx.coroutines.flow.Flow

interface CityEventsRepository {
    fun cityEventsPagerFlow(pageSize: Int, cityId: String): Flow<PagingData<CityEvent>>
}