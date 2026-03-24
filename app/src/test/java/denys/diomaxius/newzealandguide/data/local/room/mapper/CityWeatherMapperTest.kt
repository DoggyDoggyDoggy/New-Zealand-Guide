package denys.diomaxius.newzealandguide.data.local.room.mapper

import com.google.common.truth.Truth.assertThat
import denys.diomaxius.newzealandguide.data.local.room.model.city.CityWeatherEntity
import org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test
import java.time.format.DateTimeParseException

class CityWeatherMapperTest {
    @Test
    fun `toDomain should correctly map weather with valid date`() {
        val entity = CityWeatherEntity(
            cityId = "queenstown_01",
            temp = 15.5,
            description = "Cloudy",
            icon = "04d",
            dateTime = "2026-03-19 14:00:00"
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEqualTo("queenstown_01")
        assertThat(domain.temp).isEqualTo(15.5)
        assertThat(domain.description).isEqualTo("Cloudy")
        assertThat(domain.icon).isEqualTo("04d")

        assertThat(domain.dateTime.year).isEqualTo(2026)
        assertThat(domain.dateTime.monthValue).isEqualTo(3)
        assertThat(domain.dateTime.dayOfMonth).isEqualTo(19)
        assertThat(domain.dateTime.hour).isEqualTo(14)
    }

    @Test
    fun `toDomain should throw exception when date format is wrong`() {
        val entity = CityWeatherEntity(
            cityId = "id",
            temp = 10.0,
            description = "desc",
            icon = "icon",
            dateTime = "19.03.2026 14:00" // Wrong format
        )

        assertThrows(DateTimeParseException::class.java) {
            entity.toDomain()
        }
    }

    @Test
    fun `toDomain should handle empty strings except for dateTime`() {
        val entity = CityWeatherEntity(
            cityId = "",
            temp = 0.0,
            description = "",
            icon = "",
            dateTime = "2026-01-01 00:00:00"
        )

        val domain = entity.toDomain()

        assertThat(domain.cityId).isEmpty()
        assertThat(domain.temp).isEqualTo(0.0)
        assertThat(domain.description).isEmpty()
        assertThat(domain.icon).isEmpty()

        assertThat(domain.dateTime.year).isEqualTo(2026)
        assertThat(domain.dateTime.monthValue).isEqualTo(1)
    }

    @Test
    fun `toDomain should throw exception when dateTime is empty string`() {
        val entity = CityWeatherEntity(
            cityId = "id",
            temp = 20.0,
            description = "desc",
            icon = "icon",
            dateTime = "" // Empty string instead of valid date
        )

        assertThrows(DateTimeParseException::class.java) {
            entity.toDomain()
        }
    }
}