package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Embedded
import androidx.room.Relation

data class CityWithDetails(
    @Embedded val city: CityEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val weather: CityWeatherEntity
)