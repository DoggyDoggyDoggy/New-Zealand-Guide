package denys.diomaxius.newzealandguide.data.local.room.model.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val key: String,
    val lastCreatedAt: Long,
    val lastDocId: String
)