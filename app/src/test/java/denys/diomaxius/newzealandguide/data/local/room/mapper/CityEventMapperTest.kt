package denys.diomaxius.newzealandguide.data.local.room.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityEventEntity
import org.junit.jupiter.api.Test

class CityEventMapperTest {
    @Test
    fun `toDomain should correctly map CityEventEntity to CityEvent`() {
        val entity = CityEventEntity(
            cityId = "1",
            eventId = "event_123",
            url = "https://nz.com",
            name = "Rugby Match",
            description = "Big game in Auckland",
            address = "Eden Park",
            imageUrl = "https://image.com",
            sessions = listOf("19:00", "21:00"),
            positionInList = 5,
            favorite = true
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEqualTo(entity.cityId)
        assertThat(domain.eventId).isEqualTo(entity.eventId)
        assertThat(domain.url).isEqualTo(entity.url)
        assertThat(domain.name).isEqualTo(entity.name)
        assertThat(domain.description).isEqualTo(entity.description)
        assertThat(domain.address).isEqualTo(entity.address)
        assertThat(domain.imageUrl).isEqualTo(entity.imageUrl)
        assertThat(domain.sessions).isEqualTo(entity.sessions)
        assertThat(domain.positionInList).isEqualTo(entity.positionInList)
        assertThat(domain.favorite).isTrue()
    }

    @Test
    fun `toDomain should handle empty or default values`() {
        val entity = CityEventEntity(
            cityId = "",
            eventId = "",
            url = "",
            name = "",
            description = "",
            address = "",
            imageUrl = "",
            sessions = emptyList(),
            positionInList = 0,
            favorite = false
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEmpty()
        assertThat(domain.eventId).isEmpty()
        assertThat(domain.url).isEmpty()
        assertThat(domain.name).isEmpty()
        assertThat(domain.description).isEmpty()
        assertThat(domain.address).isEmpty()
        assertThat(domain.imageUrl).isEmpty()
        assertThat(domain.sessions).isEmpty()
        assertThat(domain.positionInList).isEqualTo(0)
        assertThat(domain.favorite).isFalse()
    }
}