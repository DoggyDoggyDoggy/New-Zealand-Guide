package denys.diomaxius.newzealandguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import denys.diomaxius.newzealandguide.navigation.AppNavigation
import denys.diomaxius.newzealandguide.ui.theme.NewZealandGuideTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewZealandGuideTheme {
                AppNavigation()
            }
        }
    }
}