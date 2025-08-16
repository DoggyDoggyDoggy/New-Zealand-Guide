package denys.diomaxius.newzealandguide.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.repository.CityRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.EventRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.HomeRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.MaoriWordsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandFactsRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.NewZealandHistoryRepositoryImpl
import denys.diomaxius.newzealandguide.data.repository.WeatherRepositoryImpl
import denys.diomaxius.newzealandguide.domain.repository.CityRepository
import denys.diomaxius.newzealandguide.domain.repository.EventRepository
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        Firebase.firestore

    @Provides
    @Singleton
    fun provideCityRepository(firestore: FirebaseFirestore): CityRepository =
        CityRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideWeatherRepository(firestore: FirebaseFirestore): WeatherRepository =
        WeatherRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideEventRepository(firestore: FirebaseFirestore): EventRepository =
        EventRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideHomeRepository(firestore: FirebaseFirestore): HomeRepository =
        HomeRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideMaoriWordsRepository(firestore: FirebaseFirestore): MaoriWordsRepository =
        MaoriWordsRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideNewZealandHistoryRepository(firestore: FirebaseFirestore): NewZealandHistoryRepository =
        NewZealandHistoryRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideNewZealandFactsRepository(firestore: FirebaseFirestore): NewZealandFactsRepository =
        NewZealandFactsRepositoryImpl(firestore)
}