package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val photos: List<String>,
    val favorite: Boolean
)