package denys.diomaxius.newzealandguide.data.repository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.confirmVerified

import android.content.Context
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
import kotlinx.coroutines.test.runTest

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
}