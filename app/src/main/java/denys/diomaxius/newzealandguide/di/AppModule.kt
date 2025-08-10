package denys.diomaxius.newzealandguide.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.EventRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.HomeRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.MaoriWordsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandHistoryRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.WeatherRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCityRepository(): CityRepository = CityRepositoryImpl()

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository = WeatherRepositoryImpl()

    @Provides
    @Singleton
    fun provideEventRepository(): EventRepository = EventRepositoryImpl()

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository = HomeRepositoryImpl()

    @Provides
    @Singleton
    fun provideMaoriWordsRepository(): MaoriWordsRepository = MaoriWordsRepositoryImpl()

    @Provides
    @Singleton
    fun provideNewZealandHistoryRepository(): NewZealandHistoryRepository =
        NewZealandHistoryRepositoryImpl()
}