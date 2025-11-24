package denys.diomaxius.newzealandguide.data.remote.api

import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto

interface CityEventsDataSource {
    suspend fun getEvents(cityId: String, limit: Int, lastDocId: String?): List<CityEventDto>
}