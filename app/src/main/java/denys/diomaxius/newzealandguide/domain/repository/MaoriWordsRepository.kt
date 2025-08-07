package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords

interface MaoriWordsRepository {
    suspend fun getMaoriWords(): MaoriWords
}