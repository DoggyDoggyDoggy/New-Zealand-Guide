package denys.diomaxius.newzealandguide.ui.screen.maorilearningresources

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.domain.model.maorihub.MaoriLearningResources
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.components.topbar.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaoriLearningResourcesScreen(
    viewModel: MaoriLearningResourcesScreenViewModel = hiltViewModel(),
) {
    val resources = viewModel.maoriLearningResources

    val navHostController = LocalNavController.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.top_bar_learning_resources),
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.navigateUp()
                    }
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            resources = resources,
            context = context
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    resources: List<MaoriLearningResources>,
    context: Context
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        items(resources) {
            ResourceCard(
                title = it.title,
                description = it.description,
                url = it.url,
                context = context
            )
        }
        item{
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}