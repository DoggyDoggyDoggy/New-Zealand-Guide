package denys.diomaxius.newzealandguide.data.local.room.model.cityhistory

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity

@Entity(
    tableName = "city_history",
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
data class CityHistoryEntity(
    @PrimaryKey
    val cityId: String,
    val paragraphs: List<String>
)