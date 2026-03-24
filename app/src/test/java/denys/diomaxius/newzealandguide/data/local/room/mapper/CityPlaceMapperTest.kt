package denys.diomaxius.newzealandguide.data.local.room.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityPlaceEntity
import org.junit.jupiter.api.Test

class CityPlaceMapperTest {

    @Test
    fun `toDomain should correctly map cityId, name, image and description`() {
        val entity = CityPlaceEntity(
            cityId = "hamilton_01",
            name = "Hamilton Gardens",
            image = "https://nz-images.com/gardens.jpg",
            description = "Beautiful themed gardens in Hamilton."
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEqualTo("hamilton_01")
        assertThat(domain.name).isEqualTo("Hamilton Gardens")
        assertThat(domain.image).isEqualTo("https://nz-images.com/gardens.jpg")
        assertThat(domain.description).isEqualTo("Beautiful themed gardens in Hamilton.")
    }

    @Test
    fun `toDomain should handle empty strings`() {
        val entity = CityPlaceEntity(
            cityId = "",
            name = "",
            image = "",
            description = ""
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEmpty()
        assertThat(domain.name).isEmpty()
        assertThat(domain.image).isEmpty()
        assertThat(domain.description).isEmpty()
    }
}