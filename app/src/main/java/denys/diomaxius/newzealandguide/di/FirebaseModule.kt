package denys.diomaxius.newzealandguide.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.remote.api.CityEventsDataSource
import denys.diomaxius.newzealandguide.data.remote.api.CityWeatherDataSource
import denys.diomaxius.newzealandguide.data.remote.datasource.CityEventsDataSourceImpl
import denys.diomaxius.newzealandguide.data.remote.datasource.CityWeatherDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        Firebase.firestore

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
}