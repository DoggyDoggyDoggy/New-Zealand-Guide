package denys.diomaxius.newzealandguide.ui.screen.event.components

import android.content.Context
import android.content.Intent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import denys.diomaxius.newzealandguide.domain.model.city.CityEvent

fun shareEvent(context: Context, event: CityEvent) {
    val shareText = buildString {
        appendLine("Event:")
        appendLine(event.name)
        appendLine()

        appendLine("Description:")
        appendLine(AnnotatedString.fromHtml(event.description))
        appendLine()

        appendLine("Address:")
        appendLine(event.address)
        appendLine()

        val displayedSessions = event.sessions.take(5).joinToString("\n")
        val dateHeader = if (event.sessions.size > 1) "Dates:" else "Date:"
        appendLine(dateHeader)
        append(displayedSessions)

        if (event.sessions.size > 5) {
            append("\nand more..")
        }
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(sendIntent, "Share Event"))
}