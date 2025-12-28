package denys.diomaxius.newzealandguide.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource

@OptIn(ExperimentalPagingApi::class)
class CityEventsRemoteMediator(
    private val cityId: String,
    private val pageSize: Int,
    private val dataSource: CityEventsDataSource,
    private val remoteKeysDao: RemoteCityEventsKeysDao,
    private val database: CityDatabase,
) : RemoteMediator<Int, CityEventEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CityEventEntity>,
    ): MediatorResult {
        TODO("Provide the return value")
    }
}