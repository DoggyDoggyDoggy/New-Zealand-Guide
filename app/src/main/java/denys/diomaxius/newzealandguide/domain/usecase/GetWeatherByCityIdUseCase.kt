package denys.diomaxius.newzealandguide.domain.usecase

import denys.diomaxius.newzealandguide.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityIdUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityId: String) = repository.getWeatherByCityId(cityId)
}