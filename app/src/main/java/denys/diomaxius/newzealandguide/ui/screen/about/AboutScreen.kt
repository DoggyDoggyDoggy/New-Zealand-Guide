package denys.diomaxius.newzealandguide.ui.screen.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import denys.diomaxius.newzealandguide.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    contactEmail: String = "developer@example.com"
) {
    val context = LocalContext.current

    // get versionName safely
    val versionName = remember {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "?"
        } catch (e: Exception) {
            "?"
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = stringResource(R.string.about_title), style = MaterialTheme.typography.titleLarge)
        Text(text = stringResource(R.string.about_created))
        Text(text = stringResource(R.string.about_photos))
        Text(text = stringResource(R.string.about_ai))

        Text(
            text = stringResource(R.string.about_contact, contactEmail),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    val subject = Uri.encode("NZ Guide — Feedback")
                    val uri = Uri.parse("mailto:$contactEmail?subject=$subject")
                    val intent = Intent(Intent.ACTION_SENDTO, uri)
                    // safety: check if there's an app
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
                .padding(vertical = 4.dp)
        )

        Text(text = stringResource(R.string.app_version_label, versionName), style = MaterialTheme.typography.labelSmall)
    }
}
