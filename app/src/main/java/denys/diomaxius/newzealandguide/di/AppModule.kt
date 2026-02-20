package denys.diomaxius.newzealandguide.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.local.room.dao.CityDao
import denys.diomaxius.newzealandguide.data.local.room.dao.RemoteCityEventsKeysDao
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import denys.diomaxius.newzealandguide.data.logging.ErrorLoggerImpl
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.datasource.CityEventsDataSourceImpl
import denys.diomaxius.newzealandguide.data.remote.datasource.CityWeatherDataSourceImpl
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.MaoriWordsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandFactsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandHistoryRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import denys.diomaxius.newzealandguide.domain.repository.ConnectivityObserver
import denys.diomaxius.newzealandguide.data.repository.ConnectivityObserverImpl
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
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
    fun provideFirebaseFirestore(): FirebaseFirestore =
        Firebase.firestore

    @Provides
    @Singleton
    fun provideCityDao(db: CityDatabase) = db.cityDao()

    @Provides
    @Singleton
    fun provideRemoteCityEventsKeysDao(db: CityDatabase) = db.remoteCityEventsKeysDao()

    @Provides
    @Singleton
    fun provideCityWeatherDataSource(
        firestore: FirebaseFirestore,
    ): CityWeatherDataSource =
        CityWeatherDataSourceImpl(firestore)

    @Provides
    @Singleton
    fun provideCityEventsDataSource(
        firestore: FirebaseFirestore,
    ): CityEventsDataSource =
        CityEventsDataSourceImpl(firestore)

    @Provides
    @Singleton
    fun provideCityRepository(
        @ApplicationContext context: Context,
        cityDao: CityDao,
        cityWeatherDataSource: CityWeatherDataSource,
        cityEventsDataSource: CityEventsDataSource,
        remoteCityEventsKeysDao: RemoteCityEventsKeysDao,
        database: CityDatabase,
        logger: ErrorLogger
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
    fun provideNewZealandHistoryRepository(): NewZealandHistoryRepository =
        NewZealandHistoryRepositoryImpl()

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context,
    ): ConnectivityObserver = ConnectivityObserverImpl(context)

    @Provides
    @Singleton
    fun provideErrorLogger(): ErrorLogger = ErrorLoggerImpl()
}