package denys.diomaxius.newzealandguide.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.local.room.database.CityDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        CityDatabase::class.java,
        CityDatabase.DATABASE_NAME
    ).createFromAsset("database/prepopulated.db")
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()

    @Provides
    @Singleton
    fun provideRemoteCityEventsKeysDao(db: CityDatabase) = db.remoteCityEventsKeysDao()

    @Provides
    @Singleton
    fun provideCityDao(db: CityDatabase) = db.cityDao()
}