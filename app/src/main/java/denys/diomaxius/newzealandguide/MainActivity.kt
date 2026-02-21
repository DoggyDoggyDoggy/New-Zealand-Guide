package denys.diomaxius.newzealandguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import dagger.hilt.android.AndroidEntryPoint
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper
import denys.diomaxius.newzealandguide.navigation.AppNavigation
import denys.diomaxius.newzealandguide.ui.theme.NewZealandGuideTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var analyticsHelper: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(
                scrim = Color(0xFF005048).toArgb()
            )
        )
        setContent {
            NewZealandGuideTheme {
                AppNavigation(analyticsHelper = analyticsHelper)
            }
        }
    }
}