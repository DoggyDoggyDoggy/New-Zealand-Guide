package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.home.Home
import denys.diomaxius.newzealandguide.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {
    private val photos = listOf(
        "home/homephoto/1.png",
        "home/homephoto/2.png",
        "home/homephoto/3.png",
        "home/homephoto/4.png",
    )
    override fun getHomeData(): Home =
        Home(
            photos = photos
        )
}