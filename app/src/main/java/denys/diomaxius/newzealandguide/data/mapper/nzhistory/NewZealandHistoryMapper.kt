package denys.diomaxius.newzealandguide.data.mapper.nzhistory

import denys.diomaxius.newzealandguide.data.model.nzhistory.NewZealandHistoryEntity
import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory

fun NewZealandHistoryEntity.toDomain() : NewZealandHistory =
    NewZealandHistory(
        paragraphs = paragraphs,
        paragraphsTitles = paragraphsTitles
    )