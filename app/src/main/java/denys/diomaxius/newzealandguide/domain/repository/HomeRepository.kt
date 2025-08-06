package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.home.Home

interface HomeRepository {
    suspend fun getHomeData(): Home
}