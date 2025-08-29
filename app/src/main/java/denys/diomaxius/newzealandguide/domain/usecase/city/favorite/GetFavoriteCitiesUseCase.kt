package denys.diomaxius.newzealandguide.domain.usecase.city.favorite

import denys.diomaxius.newzealandguide.data.datastore.FavoriteCitiesDataStore
import javax.inject.Inject


class GetFavoriteCitiesUseCase @Inject constructor(
    private val favoriteCitiesDataStore: FavoriteCitiesDataStore
) {
    operator fun invoke() = favoriteCitiesDataStore.favoriteCityIds
}