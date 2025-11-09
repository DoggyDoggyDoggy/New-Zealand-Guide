package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts

interface NewZealandFactsRepository {
    fun getNewZealandFacts(): NewZealandFacts
}