package denys.diomaxius.newzealandguide.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.local.datastore.OnboardingManager
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.MaoriLearningResourcesRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.MaoriWordsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandFactsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandHistoryRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.OnboardingRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
import denys.diomaxius.newzealandguide.domain.repository.MaoriLearningResourcesRepository
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import denys.diomaxius.newzealandguide.domain.repository.OnboardingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCityRepository(
        @ApplicationContext context: Context,
        cityDao: CityDao,
        cityWeatherDataSource: CityWeatherDataSource,
        cityEventsDataSource: CityEventsDataSource,
        remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
        database: CityDatabase,
        logger: ErrorLogger,
    ): CityRepository =
        CityRepositoryImpl(
            context,
            cityDao,
            cityWeatherDataSource,
            cityEventsDataSource,
            remoteCityEventsKeysDao,
            database,
            logger
        )

    @Provides
    @Singleton
    fun provideNewZealandFactsRepository(): NewZealandFactsRepository =
        NewZealandFactsRepositoryImpl()

    @Provides
    @Singleton
    fun provideMaoriWordsRepository(): MaoriWordsRepository =
        MaoriWordsRepositoryImpl()

    @Provides
    @Singleton
    fun provideMaoriLearningResourcesRepository(): MaoriLearningResourcesRepository =
        MaoriLearningResourcesRepositoryImpl()

    @Provides
    @Singleton
    fun provideNewZealandHistoryRepository(): NewZealandHistoryRepository =
        NewZealandHistoryRepositoryImpl()

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        onboardingManager: OnboardingManager
    ): OnboardingRepository = OnboardingRepositoryImpl(onboardingManager)
}