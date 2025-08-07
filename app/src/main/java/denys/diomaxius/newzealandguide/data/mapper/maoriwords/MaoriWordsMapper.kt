package denys.diomaxius.newzealandguide.data.mapper.maoriwords

import denys.diomaxius.newzealandguide.data.model.maoriwords.MaoriWordsEntity
import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords

fun MaoriWordsEntity.toDomain(): MaoriWords =
    MaoriWords(words = words)