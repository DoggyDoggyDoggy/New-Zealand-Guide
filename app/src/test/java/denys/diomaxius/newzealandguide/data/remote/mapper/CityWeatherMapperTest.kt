package denys.diomaxius.newzealandguide.data.remote.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.remote.model.CityWeatherDto
import org.junit.jupiter.api.Test

class CityWeatherMapperTest {
    @Test
    fun `toEntity should correctly map DTO fields and use provided cityId`() {
        val dto = CityWeatherDto(
            temp = 22.5,
            descr = "Partly Cloudy",
            icon = "03d",
            dt_txt = "2026-03-20 10:00:00"
        )
        val inputCityId = "hamilton_west"

        val entity = dto.toEntity(inputCityId)

        assertThat(entity.cityId).isEqualTo("hamilton_west")
        assertThat(entity.temp).isEqualTo(22.5)
        assertThat(entity.description).isEqualTo("Partly Cloudy")
        assertThat(entity.icon).isEqualTo("03d")
        assertThat(entity.dateTime).isEqualTo("2026-03-20 10:00:00")
    }

    @Test
    fun `toEntity should handle default DTO values`() {
        val emptyDto = CityWeatherDto() // Use default values from the constructor
        val cityId = "id"

        val entity = emptyDto.toEntity(cityId)

        assertThat(entity.temp).isEqualTo(0.0)
        assertThat(entity.description).isEmpty()
        assertThat(entity.icon).isEmpty()
        assertThat(entity.dateTime).isEmpty()
    }
}