package denys.diomaxius.newzealandguide.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.HomeRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandFactsRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        CityDatabase::class.java,
        CityDatabase.DATABASE_NAME
    ).createFromAsset("database/prepopulated.db")
     .build()

    @Provides
    @Singleton
    fun provideCityDao(db: CityDatabase) = db.cityDao()

    @Provides
    @Singleton
    fun provideCityRepository(cityDao: CityDao): CityRepository =
        CityRepositoryImpl(cityDao)

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository =
        HomeRepositoryImpl()

    @Provides
    @Singleton
    fun provideNewZealandFactsRepository(): NewZealandFactsRepository =
        NewZealandFactsRepositoryImpl()
}