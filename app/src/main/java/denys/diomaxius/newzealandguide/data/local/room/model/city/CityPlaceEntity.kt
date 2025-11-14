package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "city_place",
    primaryKeys = ["cityId", "name"],
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index("cityId")]
)
data class CityPlaceEntity(
    val cityId: String,
    val name: String,
    val image: String,
    val description: String
)