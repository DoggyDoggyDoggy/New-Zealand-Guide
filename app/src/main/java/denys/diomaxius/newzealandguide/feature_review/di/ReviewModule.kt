package denys.diomaxius.newzealandguide.feature_review.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.newzealandguide.feature_review.data.FeedbackRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewModule {

    @Provides
    @Singleton
    fun provideFeedbackRepository(
        @ApplicationContext context: Context
    ): FeedbackRepository {
        return FeedbackRepository(context)
    }
}