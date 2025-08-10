package denys.diomaxius.newzealandguide.data.mapper.nzfacts

import denys.diomaxius.newzealandguide.data.model.nzfacts.NewZealandFactsEntity
import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts

fun NewZealandFactsEntity.toDomain(): NewZealandFacts =
    NewZealandFacts(
        facts = facts
    )