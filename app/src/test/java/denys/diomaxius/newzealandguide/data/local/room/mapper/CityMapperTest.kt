package denys.diomaxius.newzealandguide.data.local.room.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEntity
import org.junit.jupiter.api.Test

class CityMapperTest {

    @Test
    fun `toDomain should correctly map id, name, photos and favorite`() {
        val expectedPhotos = listOf("url_1", "url_2", "url_3")
        val entity = CityEntity(
            id = "auckland_01",
            name = "Auckland",
            photos = expectedPhotos,
            favorite = true
        )

        val domain = entity.toDomain()

        assertThat(domain.id).isEqualTo("auckland_01")
        assertThat(domain.name).isEqualTo("Auckland")
        assertThat(domain.favorite).isTrue()

        assertThat(domain.photos)
            .containsExactlyElementsIn(expectedPhotos)
            .inOrder()
    }

    @Test
    fun `toDomain should handle empty fields`() {
        val entity = CityEntity(
            id = "",
            name = "",
            photos = emptyList(),
            favorite = false
        )

        val domain = entity.toDomain()

        assertThat(domain.id).isEmpty()
        assertThat(domain.name).isEmpty()
        assertThat(domain.photos).isEmpty()
        assertThat(domain.favorite).isFalse()
    }
}