package denys.diomaxius.newzealandguide.domain.repository

import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts

interface NewZealandFactsRepository {
    suspend fun getNewZealandFacts(): NewZealandFacts
}