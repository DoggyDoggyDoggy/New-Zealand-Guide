package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriWords

interface MaoriWordsRepository {
    fun getMaoriWords(): MaoriWords
}