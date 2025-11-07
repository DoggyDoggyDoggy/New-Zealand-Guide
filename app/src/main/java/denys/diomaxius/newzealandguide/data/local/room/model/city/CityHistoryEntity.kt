package denys.diomaxius.newzealandguide.data.local.room.model.city

import androidx.room.Entity

@Entity
data class CityHistoryEntity(
    val paragraphs: List<String>
)