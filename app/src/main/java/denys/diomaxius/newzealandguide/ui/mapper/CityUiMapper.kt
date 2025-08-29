package denys.diomaxius.newzealandguide.ui.mapper

import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.ui.model.CityUi

fun City.toUi(isFavorite: Boolean) = CityUi(
    id = id,
    name = name,
    photos = photos,
    isFavorite = isFavorite
)