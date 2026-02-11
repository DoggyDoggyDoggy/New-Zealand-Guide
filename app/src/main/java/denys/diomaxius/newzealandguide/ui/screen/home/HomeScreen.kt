package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current

    Scaffold { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            navHostController = navHostController
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.home),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Kia ora!",
            fontWeight = FontWeight.Bold,
            fontSize = 52.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            style = TextStyle(
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.primary,
                    offset = Offset(5.0f, 8f),
                    blurRadius = 12f
                )
            )
        )

        Text(
            text = "Explore New Zealand",
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            style = TextStyle(
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.primary,
                    offset = Offset(5.0f, 8f),
                    blurRadius = 12f
                )
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        LongInfoCard(
            titleCardText = "Top Cities & Towns",
            subTitleCardText = "Explore New Zealand",
            icon = painterResource(id = R.drawable.outline_location_city_24),
            onClick = {
                navHostController.navigate(NavScreen.AllCities.route) {
                    launchSingleTop = true
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LongInfoCard(
            titleCardText = "Te Reo Māori",
            subTitleCardText = "Learn Te Reo",
            icon = painterResource(id = R.drawable.outline_book_3_24),
            onClick = {
                navHostController.navigate(NavScreen.MaoriHub.route) {
                    launchSingleTop = true
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LongInfoCard(
            titleCardText = "NZ History & Heritage",
            subTitleCardText = "Dive into History",
            icon = painterResource(id = R.drawable.outline_book_ribbon_24),
            onClick = {
                navHostController.navigate(NavScreen.NewZealandHistory.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}