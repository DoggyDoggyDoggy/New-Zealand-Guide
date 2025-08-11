package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.domain.model.home.Home
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.loadingscreen.ScreenLoading
import denys.diomaxius.newzealandguide.ui.common.components.navigationdrawer.NavigationDrawer
import denys.diomaxius.newzealandguide.ui.common.components.topbar.MenuButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar
import denys.diomaxius.newzealandguide.ui.common.uistate.UiStateHandler
import denys.diomaxius.newzealandguide.ui.screen.home.components.HeroBlock
import denys.diomaxius.newzealandguide.ui.screen.home.components.NavigationMenu
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val navHostController = LocalNavController.current
    val uiState by viewModel.homeUiState.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    UiStateHandler(
        state = uiState,
        loading = {
            ScreenLoading()
        }
    ) { homeData ->
        NavigationDrawer (
            drawerState = drawerState,
            navHostController = navHostController
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        text = "New Zealand Guide",
                        navigationButton = {
                            MenuButton {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
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
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeData: Home,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeroBlock(
            photos = homeData.photos
        )

        NavigationMenu(
            navHostController = navHostController
        )
    }
}