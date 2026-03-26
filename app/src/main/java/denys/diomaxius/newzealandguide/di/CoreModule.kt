package denys.diomaxius.newzealandguide.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.data.analytics.FirebaseAnalyticsHelper
import denys.diomaxius.newzealandguide.data.local.datastore.OnboardingManager
import denys.diomaxius.newzealandguide.data.logging.ErrorLoggerImpl
import denys.diomaxius.newzealandguide.data.repository.ConnectivityObserverImpl
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper
import denys.diomaxius.newzealandguide.domain.repository.ConnectivityObserver
import denys.diomaxius.newzealandguide.domain.repository.ErrorLogger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideOnboardingManager(
        @ApplicationContext context: Context
    ): OnboardingManager = OnboardingManager(context)

    @Provides
    @Singleton
    fun provideAnalyticsHelper(
        @ApplicationContext context: Context,
    ): AnalyticsHelper = FirebaseAnalyticsHelper(context)

    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext context: Context,
    ): ConnectivityObserver = ConnectivityObserverImpl(context)

    @Provides
    @Singleton
    fun provideErrorLogger(): ErrorLogger = ErrorLoggerImpl()
}