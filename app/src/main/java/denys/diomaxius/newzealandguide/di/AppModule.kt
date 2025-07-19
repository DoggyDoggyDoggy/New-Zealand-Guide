package denys.diomaxius.newzealandguide.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.WeatherRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
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
}