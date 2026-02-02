package denys.diomaxius.newzealandguide.domain.repository

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val hasInternetConnection: Flow<Boolean>
}