package denys.diomaxius.newzealandguide.domain.model.city

sealed class WeatherResult {
    data class Success(val data: List<CityWeather>) : WeatherResult()
    data class Error(val exception: Throwable) : WeatherResult()
}