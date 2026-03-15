package denys.diomaxius.newzealandguide.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.window.Dialog
import dagger.hilt.android.AndroidEntryPoint
import denys.diomaxius.newzealandguide.domain.repository.AnalyticsHelper
import denys.diomaxius.newzealandguide.feature_review.ui.ReviewScreen
import denys.diomaxius.newzealandguide.navigation.AppNavigation
import denys.diomaxius.newzealandguide.ui.theme.NewZealandGuideTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.Companion.dark(
                scrim = Color(0xFF005048).toArgb()
            )
        )
        setContent {
            val startRoute = viewModel.startDestination.value
            val showDialog by viewModel.showReviewDialog.collectAsState()

            NewZealandGuideTheme {
                if (startRoute != null) {
                    AppNavigation(
                        analyticsHelper = analyticsHelper,
                        startDestination = startRoute
                    )

                    if (showDialog) {
                        Dialog(
                            onDismissRequest = { viewModel.onReviewDismissed() }
                        ) {
                            ReviewScreen(
                                onDismiss = {
                                    viewModel.onReviewDismissed()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}