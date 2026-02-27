package denys.diomaxius.newzealandguide.domain.usecase.maorihub

import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class GetMaoriWordOfTheDayUseCase @Inject constructor(
    private val maoriWordsRepository: MaoriWordsRepository,
) {
    operator fun invoke(): Pair<String, String> {

        val words = maoriWordsRepository.getMaoriWords()

        val today = LocalDate.now(ZoneId.of("Pacific/Auckland"))
        val seed = today.toEpochDay()

        val entries = words.words.entries.toList()

        val index = (seed % entries.size).toInt()

        val entry = entries[index]

        return entry.key to entry.value
    }
}