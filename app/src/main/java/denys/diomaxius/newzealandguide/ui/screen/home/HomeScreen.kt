package denys.diomaxius.newzealandguide.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.navigation.NavScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navHostController = LocalNavController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")
        Button(
            onClick = {
                navHostController.navigate(
                    NavScreen.AllCities.route
                ) {
                    launchSingleTop = true
                }
            },
            modifier = modifier
        ) {
            Text("Cities")
        }
    }
}