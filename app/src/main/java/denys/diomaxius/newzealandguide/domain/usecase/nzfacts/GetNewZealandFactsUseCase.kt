package denys.diomaxius.newzealandguide.domain.usecase.nzfacts

import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository
import javax.inject.Inject

class GetNewZealandFactsUseCase @Inject constructor(
    private val repository: NewZealandFactsRepository,
) {
    operator fun invoke(): NewZealandFacts = repository.getNewZealandFacts()
}