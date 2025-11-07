package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityPlacesEntity(
    @PrimaryKey
    val name: String,
    val image: String,
    val description: String
)
