package denys.diomaxius.newzealandguide.ui.screen.about

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import denys.diomaxius.newzealandguide.R
import denys.diomaxius.newzealandguide.navigation.LocalNavController
import denys.diomaxius.newzealandguide.ui.common.components.topbar.PopBackArrowButton
import denys.diomaxius.newzealandguide.ui.common.components.topbar.TopBar

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val navHostController = LocalNavController.current

    // get versionName safely
    val versionName = remember {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "?"
        } catch (e: Exception) {
            "?"
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.about_title),
                navigationButton = {
                    PopBackArrowButton {
                        navHostController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        Content(
            modifier = modifier.padding(innerPadding),
            context = context,
            versionName = versionName
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    context: Context,
    versionName: String
) {
    val unsplashLicenseUrl = stringResource(R.string.unsplash_license_url)
    val contactEmail: String = stringResource(R.string.email_for_reports)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = stringResource(R.string.about_photos))
        Text(
            modifier = Modifier.clickable {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, unsplashLicenseUrl.toUri())
                )
            },
            text = unsplashLicenseUrl,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = stringResource(R.string.about_ai))

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = stringResource(R.string.about_created))

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Text(text = stringResource(R.string.about_contact))

            Text(
                text = contactEmail,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        val subject = Uri.encode("NZ Guide — Feedback")
                        val uri = "mailto:$contactEmail?subject=$subject".toUri()
                        val intent = Intent(Intent.ACTION_SENDTO, uri)

                        val chooser = Intent.createChooser(intent, "Send email")

                        if (context !is Activity) chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                        try {
                            context.startActivity(chooser)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "No email app installed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.app_version_label, versionName),
            style = MaterialTheme.typography.labelMedium
        )
    }
}