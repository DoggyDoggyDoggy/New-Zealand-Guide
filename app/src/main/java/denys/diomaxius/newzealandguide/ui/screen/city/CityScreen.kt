package denys.diomaxius.newzealandguide.ui.screen.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import denys.diomaxius.newzealandguide.R
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.newzealandguide.domain.model.city.City
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent
import denys.diomaxius.newzealandguide.domain.model.city.CityWeather
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.components.cityphotoslider.CityPhotoSlider
import denys.diomaxius.newzealandguide.ui.screen.city.components.events.Events
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.components.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.city.components.ColumnOfTwoLongInfoCards
import denys.diomaxius.newzealandguide.ui.screen.city.components.weather.WeatherForecastFiveDays
import denys.diomaxius.newzealandguide.ui.components.ScreenLoading
import denys.diomaxius.newzealandguide.ui.components.topbar.DummyAction
import denys.diomaxius.newzealandguide.ui.components.uistate.UiState
import denys.diomaxius.newzealandguide.ui.screen.city.components.NoLocalCacheCard

@Composable
fun CityScreen(
    viewModel: CityScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current

    val uiState by viewModel.uiState.collectAsState()
    val eventsPagingItems = viewModel.events.collectAsLazyPagingItems()
    val hasInternetConnection by viewModel.hasInternetConnection.collectAsState()

    val showNoLocalCacheCard by remember(
        hasInternetConnection,
        eventsPagingItems.loadState.refresh,
        eventsPagingItems.itemCount,
        uiState.weather
    ) {
        mutableStateOf(
            !hasInternetConnection &&
                    eventsPagingItems.loadState.refresh is LoadState.Error &&
                    eventsPagingItems.itemCount == 0 &&
                    uiState.weather is UiState.Error
        )
    }


    LaunchedEffect(hasInternetConnection) {
        if (eventsPagingItems.loadState.refresh is LoadState.Error &&
            eventsPagingItems.itemCount == 0 && hasInternetConnection &&
            uiState.weather is UiState.Error
        ) {
            eventsPagingItems.retry()
            viewModel.loadWeather()
        }
    }

    UiStateHandler(
        state = uiState.city,
        loading = { ScreenLoading() }
    ) { city ->
        Scaffold(
            topBar = {
                TopBar(
                    text = city.name,
                    navigationButton = {
                        PopBackArrowButton {
                            navHostController.navigateUp()
                        }
                    },
                    actions = {
                        if (!hasInternetConnection) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .size(32.dp),
                                painter = painterResource(id = R.drawable.outline_android_wifi_3_bar_off_24),
                                contentDescription = "No internet connection",
                                tint = Color.White
                            )
                        } else {
                            DummyAction()
                        }
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                city = city,
                navHostController = navHostController,
                weather = uiState.weather,
                eventsPagingItems = eventsPagingItems,
                showNoLocalCacheCard = showNoLocalCacheCard
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    city: City,
    navHostController: NavHostController,
    weather: UiState<List<CityWeather>>,
    eventsPagingItems: LazyPagingItems<CityEvent>,
    showNoLocalCacheCard: Boolean,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CityPhotoSlider(
            modifier = Modifier
                .shadow(12.dp),
            photos = city.photos
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (showNoLocalCacheCard) {
            NoLocalCacheCard(modifier = Modifier.height(175.dp))
        } else {
            WeatherForecastFiveDays(
                weatherUiState = weather,
                isEventsLoading = eventsPagingItems.loadState.refresh is LoadState.Loading
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Events(
                events = eventsPagingItems,
                onClick = { cityId, eventId ->
                    navHostController.navigate(
                        NavScreen.Event.createRoute(cityId, eventId)
                    ) {
                        launchSingleTop = true
                    }
                }
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ColumnOfTwoLongInfoCards(
            modifier = Modifier.padding(horizontal = 6.dp),
            navHostController = navHostController,
            city = city
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}