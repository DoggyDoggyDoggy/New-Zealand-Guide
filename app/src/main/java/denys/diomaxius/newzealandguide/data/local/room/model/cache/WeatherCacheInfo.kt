package denys.diomaxius.newzealandguide.data.local.room.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "weather_cache_info")
data class WeatherCacheInfo(
    @PrimaryKey val cityId: String,
    val lastSyncedTimestamp: Instant
)