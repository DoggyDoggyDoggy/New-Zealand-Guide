package denys.diomaxius.newzealandguide.ui.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val hasInternetConnection: Flow<Boolean>
}