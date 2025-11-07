package denys.diomaxius.newzealandguide.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import denys.diomaxius.newzealandguide.data.local.mapper.toDomain
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.domain.model.City
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepositoryImpl(
    private val cityDao: CityDao,
) : CityRepository {
    override fun citiesPagerFlow(pageSize: Int): Flow<PagingData<City>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = pageSize,
                prefetchDistance = pageSize / 2
            ),
            pagingSourceFactory = { cityDao.citiesPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }

    override suspend fun getCityById(cityId: String): City =
        cityDao.getCityById(cityId).toDomain()
}