package denys.diomaxius.newzealandguide.domain.repository

import androidx.paging.PagingData
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.model.city.CityHistory
import denys.diomaxius.newzealandguide.domain.model.city.CityPlace
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun citiesPagerFlow(pageSize: Int): Flow<PagingData<City>>
    suspend fun getCityById(cityId: String): City
    suspend fun getPlacesForCityById(cityId: String) : List<CityPlace>
    suspend fun getCityHistoryByCityId(cityId: String): CityHistory
    suspend fun getCityWeatherByCityId(cityId: String): List<CityWeather>
    fun cityEventsPagerFlow(pageSize: Int, cityId: String): Flow<PagingData<CityEvent>>
}