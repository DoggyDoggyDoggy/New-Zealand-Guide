package denys.diomaxius.newzealandguide.data.local.room.model.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_city_events_keys")
data class RemoteCityEventsKeysEntity(
    @PrimaryKey val cityId: String,
    val lastDocId: String?,
    val lastUpdated: Long = System.currentTimeMillis()
)