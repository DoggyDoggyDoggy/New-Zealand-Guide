package denys.diomaxius.newzealandguide.data.local.room.model.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "remote_city_events_keys")
data class RemoteCityEventsKeysEntity(
    @PrimaryKey val cityId: String,
    val lastDocId: String?,
    val lastSyncedTimestamp: Instant
)