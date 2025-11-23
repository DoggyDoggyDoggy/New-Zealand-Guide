package denys.diomaxius.newzealandguide.data.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity

@OptIn(ExperimentalPagingApi::class)
class CityEventsRemoteMediator(
    private val pageSize: Int,
) : RemoteMediator<Int, CityEventEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CityEventEntity>,
    ): MediatorResult {
        val limit = state.config.pageSize.takeIf { it > 0 } ?: pageSize
        TODO("Not yet implemented")
    }
}