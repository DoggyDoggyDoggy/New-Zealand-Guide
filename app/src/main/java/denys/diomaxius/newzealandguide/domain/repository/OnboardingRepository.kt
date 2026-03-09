package denys.diomaxius.newzealandguide.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getOnboardingStatus(): Flow<Boolean>
    suspend fun saveOnboardingStatus()
}