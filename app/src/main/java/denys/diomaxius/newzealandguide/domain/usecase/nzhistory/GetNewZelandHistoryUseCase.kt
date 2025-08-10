package denys.diomaxius.newzealandguide.domain.usecase.nzhistory

import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository
import javax.inject.Inject

class GetNewZealandHistoryUseCase @Inject constructor(
    private val repository: NewZealandHistoryRepository
) {
    suspend operator fun invoke(): NewZealandHistory = repository.getNewZealandHistory()
}