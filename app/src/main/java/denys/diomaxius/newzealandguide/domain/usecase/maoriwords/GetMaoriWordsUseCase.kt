package denys.diomaxius.newzealandguide.domain.usecase.maoriwords

import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriWords
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import javax.inject.Inject

class GetMaoriWordsUseCase @Inject constructor(
    private val maoriWordsRepository: MaoriWordsRepository,
) {
    operator fun invoke(): MaoriWords = maoriWordsRepository.getMaoriWords()
}