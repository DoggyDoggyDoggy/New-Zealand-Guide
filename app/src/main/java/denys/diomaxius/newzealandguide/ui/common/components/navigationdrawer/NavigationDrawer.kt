package denys.diomaxius.newzealandguide.ui.common.components.navigationdrawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.NavScreen

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navHostController: NavHostController,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navHostController = navHostController)
        },
        content = content,
    )
}

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    ModalDrawerSheet(
        modifier = modifier.width(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )

            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(R.string.nav_drawer_about),
                        textAlign = TextAlign.Center
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info"
                    )
                },
                selected = false,
                onClick = {
                    navHostController.navigate(NavScreen.About.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}