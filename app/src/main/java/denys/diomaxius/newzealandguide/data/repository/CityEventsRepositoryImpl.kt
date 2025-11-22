package denys.diomaxius.newzealandguide.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.mapper.toDomain
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.repository.city.CityEventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map as flowMap

class CityEventsRepositoryImpl(
    private val cityDao: CityDao,
) : CityEventsRepository {
    override fun cityEventsPagerFlow(pageSize: Int, cityId: String): Flow<PagingData<CityEvent>> {
        val pagingSourceFactory = { cityDao.getCityEventsPagingSource(cityId) }

        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = pageSize,
                prefetchDistance = pageSize / 2
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .flowMap { pagingData ->
                pagingData.map { entity -> entity.toDomain() }
            }
    }
}