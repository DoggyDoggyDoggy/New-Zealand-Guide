package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.Home

interface HomeRepository {
    fun getHomeData(): Home
}