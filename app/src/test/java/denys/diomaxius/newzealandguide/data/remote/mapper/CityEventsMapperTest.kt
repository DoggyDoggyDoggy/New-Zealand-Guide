package denys.diomaxius.newzealandguide.data.remote.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.remote.model.CityEventDto
import org.junit.jupiter.api.Test

class CityEventsMapperTest {
    @Test
    fun `toEntity should correctly map all DTO fields and use cityId`() {
        val dto = CityEventDto(
            address = "Queen St, Auckland",
            description = "Annual music festival",
            eventId = "evt_999",
            imageUrl = "https://nz.com/image.png",
            name = "St Jerome's Laneway",
            sessions = listOf("12:00", "18:00"),
            url = "https://lanewayfestival.com"
        )
        val inputCityId = "auckland_id"

        val entity = dto.toEntity(inputCityId)

        assertThat(entity.cityId).isEqualTo("auckland_id")
        assertThat(entity.eventId).isEqualTo("evt_999")
        assertThat(entity.url).isEqualTo("https://lanewayfestival.com")
        assertThat(entity.name).isEqualTo("St Jerome's Laneway")
        assertThat(entity.description).isEqualTo("Annual music festival")
        assertThat(entity.address).isEqualTo("Queen St, Auckland")
        assertThat(entity.imageUrl).isEqualTo("https://nz.com/image.png")
        assertThat(entity.sessions).containsExactly("12:00", "18:00").inOrder()
    }

    @Test
    fun `toEntity should handle empty DTO fields`() {
        val emptyDto = CityEventDto()
        val cityId = "some_city"

        val entity = emptyDto.toEntity(cityId)

        assertThat(entity.cityId).isEqualTo("some_city")
        assertThat(entity.eventId).isEmpty()
        assertThat(entity.url).isEmpty()
        assertThat(entity.name).isEmpty()
        assertThat(entity.description).isEmpty()
        assertThat(entity.address).isEmpty()
        assertThat(entity.imageUrl).isEmpty()
        assertThat(entity.sessions).isEmpty()
    }
}