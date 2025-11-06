package denys.diomaxius.newzealandguide.data.local.room.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_info")
data class CacheInfoEntity(
    @PrimaryKey val key: String,
    val lastSyncedEpochDay: Long
)