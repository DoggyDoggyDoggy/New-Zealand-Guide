package denys.diomaxius.newzealandguide.data.local.room.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityHistoryEntity
import org.junit.jupiter.api.Test

class CityHistoryMapperTest {

    @Test
    fun `toDomain should correctly map cityId and paragraphs`() {
        val expectedParagraphs = listOf(
            "First paragraph about history",
            "Second paragraph with more details"
        )
        val entity = CityHistoryEntity(
            cityId = "hamilton_id",
            paragraphs = expectedParagraphs
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEqualTo("hamilton_id")
        assertThat(domain.paragraphs).containsExactlyElementsIn(expectedParagraphs).inOrder()
    }

    @Test
    fun `toDomain should handle empty paragraphs list`() {
        val entity = CityHistoryEntity(
            cityId = "empty_city",
            paragraphs = emptyList()
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEqualTo("empty_city")
        assertThat(domain.paragraphs).isEmpty()
    }
}