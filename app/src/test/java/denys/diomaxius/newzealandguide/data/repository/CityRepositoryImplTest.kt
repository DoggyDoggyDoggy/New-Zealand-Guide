package denys.diomaxius.newzealandguide.data.repository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.confirmVerified

import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.flowOf
import app.cash.turbine.test

import java.time.Instant
import android.content.Context
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger

class CityRepositoryImplTest {
    private val cityDao = mockk<CityDao>(relaxed = true)
    private val weatherDataSource = mockk<CityWeatherDataSource>()
    private val eventsDataSource = mockk<CityEventsDataSource>()
    private val remoteCityEventsKeysDao = mockk<RemoteCityEventsKeysDao>()
    private val database = mockk<CityDatabase>()
    private val logger = mockk<ErrorLogger>(relaxed = true)
    private val context = mockk<Context>()

    private lateinit var repository: CityRepositoryImpl

    @BeforeEach
    fun setup() {
        repository = CityRepositoryImpl(
            context, cityDao, weatherDataSource,
            eventsDataSource, remoteCityEventsKeysDao, database, logger
        )
    }

    @Test
    fun `toggleFavorite should delegate call to cityDao`() = runTest {
        val cityId = "auckland_01"

        repository.toggleFavorite(cityId)

        coVerify(exactly = 1) { cityDao.toggleFavorite(cityId) }
    }

    @Test
    fun `toggleEventFavorite should delegate call to cityDao`() = runTest {
        val cityId = "city_1"
        val eventId = "event_123"

        repository.toggleEventFavorite(cityId, eventId)

        coVerify(exactly = 1) { cityDao.toggleEventFavorite(cityId, eventId) }
    }

    @Test
    fun `getCityById should return mapped domain city`() = runTest {
        val cityId = "hamilton_01"
        val mockEntity = CityEntity(
            id = cityId,
            name = "Hamilton",
            photos = listOf("url1", "url2"),
            favorite = true
        )

        coEvery { cityDao.getCityById(cityId) } returns mockEntity

        val result = repository.getCityById(cityId)

        assertThat(result).isInstanceOf(City::class.java)

        assertThat(result.id).isEqualTo(cityId)
        assertThat(result.name).isEqualTo("Hamilton")
        assertThat(result.favorite).isTrue()

        coVerify(exactly = 1) { cityDao.getCityById(cityId) }
    }

    @Test
    fun `getPlacesForCityById should return mapped places`() = runTest {
        val cityId = "c1"
        val entities = listOf(
            CityPlaceEntity(cityId, "Park", "url1", "desc1"),
            CityPlaceEntity(cityId, "Museum", "url2", "desc2")
        )
        coEvery { cityDao.getPlacesForCity(cityId) } returns entities

        val result = repository.getPlacesForCityById(cityId)

        assertThat(result).hasSize(2)
        assertThat(result[0].name).isEqualTo("Park")
        assertThat(result[1].name).isEqualTo("Museum")
    }

    @Test
    fun `getCityHistoryByCityId should return mapped history`() = runTest {
        val cityId = "c1"
        val entity = CityHistoryEntity(cityId, listOf("Long ago...", "History Title"))
        coEvery { cityDao.getCityHistoryByCityId(cityId) } returns entity

        val result = repository.getCityHistoryByCityId(cityId)

        assertThat(result.paragraphs)
            .containsExactly("Long ago...", "History Title")
            .inOrder()
    }

    @Test
    fun `getAllCitiesFlow should emit mapped cities`() = runTest {
        val entities = listOf(
            CityEntity("1", "Auckland", listOf("url", "url2"), true)
        )

        every { cityDao.getAllCitiesFlow(any()) } returns flowOf(entities)

        repository.getAllCitiesFlow(onlyFavorites = false).test {
            val cities = awaitItem()
            assertThat(cities[0].name).isEqualTo("Auckland")
            assertThat(cities[0]).isInstanceOf(City::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `getFavoriteCityEventsFlow should emit mapped favorite events`() = runTest {
        val cityId = "c1"
        val entities = listOf(
            CityEventEntity(
                cityId,
                "e1",
                "url",
                "Match",
                "desc",
                "addr",
                "img",
                listOf(),
                1,
                true)
        )
        every { cityDao.getFavoriteCityEventsFlow(cityId) } returns flowOf(entities)

        repository.getFavoriteCityEventsFlow(cityId).test {
            val events = awaitItem()
            assertThat(events).hasSize(1)
            assertThat(events[0]).isInstanceOf(CityEvent::class.java)
            assertThat(events[0].name).isEqualTo("Match")
            awaitComplete()
        }
    }
}