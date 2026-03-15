package denys.diomaxius.newzealandguide.feature_review.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.android.play.core.review.testing.FakeReviewManager

@Composable
fun ReviewScreen(
    viewModel: ReviewViewModel = hiltViewModel(),
    onDismiss: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val activity = context.findActivity()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is ReviewEvent.LaunchGooglePlayReview -> {
                    if (activity != null) {
                        launchInAppReview(activity)
                        onDismiss()
                    }
                }

                is ReviewEvent.ShowError -> {
                }
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (state.isSuccess) {
                    Text(
                        "Thank you for your feedback! We will fix everything.",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = onDismiss) { Text("Close") }

                    Spacer(modifier = Modifier.height(8.dp))
                } else {
                    Text("How do you like our app?", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        for (i in 1..5) {
                            val isSelected = i <= state.selectedRating
                            Icon(
                                imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Star $i",
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { viewModel.onAction(ReviewAction.SelectRating(i)) }
                            )
                        }
                    }

                    if (state.showFeedbackForm) {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = state.feedbackText,
                            onValueChange = { viewModel.onAction(ReviewAction.UpdateFeedback(it)) },
                            label = { Text("What went wrong?") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.onAction(ReviewAction.SubmitFeedback) },
                            enabled = !state.isSubmitting && state.feedbackText.isNotBlank(),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                        ) {
                            if (state.isSubmitting) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                Text("Send to developer")
                            }
                        }
                    }
                }
            }
            if (!state.showFeedbackForm && !state.isSuccess) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    TextButton(
                        onClick = { },
                    ) {
                        Text("Don't show again")
                    }
                    TextButton(
                        onClick = { },
                    ) {
                        Text("Remind later")
                    }
                }
            }
        }
    }
}

private fun launchInAppReview(activity: Activity) {
    val reviewManager = FakeReviewManager(activity)

    val request = reviewManager.requestReviewFlow()
    request.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val reviewInfo = task.result
            val flow = reviewManager.launchReviewFlow(activity, reviewInfo)
            flow.addOnCompleteListener { _ ->
                Toast.makeText(activity, "Fake Review API worked successfully!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}