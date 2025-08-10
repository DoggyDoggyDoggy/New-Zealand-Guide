package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory

interface NewZealandHistoryRepository {
    suspend fun getNewZealandHistory(): NewZealandHistory
}