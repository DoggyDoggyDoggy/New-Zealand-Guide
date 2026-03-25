package denys.diomaxius.newzealandguide.domain.usecase.maorihub

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriWords
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneId

class GetMaoriWordOfTheDayUseCaseTest {

    private val repository = mockk<MaoriWordsRepository>()
    private lateinit var useCase: GetMaoriWordOfTheDayUseCase

    private val testWordsMap = mapOf(
        "Kia Ora" to "Hello",
        "Aotearoa" to "New Zealand",
        "Kai" to "Food"
    )

    @BeforeEach
    fun setup() {
        useCase = GetMaoriWordOfTheDayUseCase(repository)
        every { repository.getMaoriWords() } returns MaoriWords(words = testWordsMap)

        mockkStatic(LocalDate::class)
    }

    @AfterEach
    fun tearDown() {
        unmockkStatic(LocalDate::class)
    }

    @Test
    fun `should return specific word for fixed date`() {
        val fixedDate = LocalDate.ofEpochDay(20002)
        every { LocalDate.now(ZoneId.of("Pacific/Auckland")) } returns fixedDate

        val result = useCase()

        assertThat(result.first).isEqualTo("Aotearoa")
        assertThat(result.second).isEqualTo("New Zealand")
    }

    @Test
    fun `should return different word for next day`() {
        val day1 = LocalDate.ofEpochDay(20000)
        val day2 = LocalDate.ofEpochDay(20001)

        every { LocalDate.now(any<ZoneId>()) } returns day1
        val result1 = useCase()

        every { LocalDate.now(any<ZoneId>()) } returns day2
        val result2 = useCase()

        assertThat(result1).isNotEqualTo(result2)
    }
}