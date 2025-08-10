package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.domain.model.home.Home
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen
import denys.diomaxius.newzealandguide.ui.common.components.cityphotoslider.CityPhotoSlider
import denys.diomaxius.newzealandguide.ui.common.components.infocard.TwoInfoCardsRow
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.topbar.MenuButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val navHostController = LocalNavController.current
    val uiState by viewModel.homeUiState.collectAsState()

    UiStateHandler(
        state = uiState,
        loading = {
            ScreenLoading()
        }
    ) { homeData ->
        Scaffold(
            topBar = {
                TopBar(
                    text = "New Zealand Guide",
                    navigationButton = {
                        MenuButton {}
                    }
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                navHostController = navHostController,
                homeData = homeData
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeData: Home
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        HeroBlock(
            photos = homeData.photos
        )

        Spacer(modifier = Modifier.height(12.dp))

        TwoInfoCardsRow(
            modifier = Modifier
                .fillMaxWidth(),
            firstCardText = "Cities",
            secondCardText = "Maori Words",
            firstCardOnClick = {
                navHostController.navigate(NavScreen.AllCities.route) {
                    launchSingleTop = true
                }
            },
            secondCardOnClick = {
                navHostController.navigate(NavScreen.MaoriWords.route) {
                    launchSingleTop = true
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TwoInfoCardsRow(
            modifier = Modifier
                .fillMaxWidth(),
            firstCardText = "History",
            secondCardText = "",
            firstCardOnClick = {
                navHostController.navigate(NavScreen.NewZealandHistory.route) {
                    launchSingleTop = true
                }
            },
            secondCardOnClick = {

            }
        )
    }
}

@Composable
fun HeroBlock(
    modifier: Modifier = Modifier,
    photos: List<String>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Kia ora!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                text = "Explore New Zealand",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }

        CityPhotoSlider(
            photos = photos
        )
    }
}