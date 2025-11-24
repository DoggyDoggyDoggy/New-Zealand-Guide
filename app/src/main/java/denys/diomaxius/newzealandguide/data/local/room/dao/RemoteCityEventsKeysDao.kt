package denys.diomaxius.newzealandguide.data.local.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import denys.diomaxius.newzealandguide.data.local.room.model.remotekeys.RemoteCityEventsKeysEntity

interface RemoteCityEventsKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: RemoteCityEventsKeysEntity)

    @Query("SELECT * FROM remote_city_events_keys WHERE 'key' = :cityId")
    suspend fun getKeyByCityId(cityId: String): RemoteCityEventsKeysEntity?

    @Query("DELETE FROM remote_city_events_keys WHERE 'key' = :cityId")
    suspend fun clearKeyByCityId(cityId: String)
}