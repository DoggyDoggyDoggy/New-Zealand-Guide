package denys.diomaxius.newzealandguide.domain.model.city

sealed class WeatherResult {
    data class Success(val data: List<CityWeather>) : WeatherResult()
    object NoInternetAndNoCache : WeatherResult()
    data class Error(val exception: Throwable) : WeatherResult()
}