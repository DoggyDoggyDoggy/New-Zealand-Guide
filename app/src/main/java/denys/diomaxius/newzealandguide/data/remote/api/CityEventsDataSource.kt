package denys.diomaxius.newzealandguide.data.remote.api

import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto
import java.time.Instant

interface CityEventsDataSource {
    suspend fun getEvents(cityId: String, limit: Int, lastDocId: String?): List<CityEventDto>
    suspend fun fetchLastUpdatedAt(cityId: String): Instant?
}