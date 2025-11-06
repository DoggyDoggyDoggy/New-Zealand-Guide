package denys.diomaxius.newzealandguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import denys.diomaxius.newzealandguide.ui.theme.NewZealandGuideV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewZealandGuideV2Theme {

            }
        }
    }
}