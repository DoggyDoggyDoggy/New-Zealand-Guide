package denys.diomaxius.newzealandguide.ui.screen.event.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventDates(sessions: List<String>) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null
        )

        Column {
            if (!expanded) {
                Text(
                    text = sessions.first(),
                    fontSize = 16.sp,
                )
            } else {
                sessions.forEach { session ->
                    Text(
                        text = session,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 1.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (sessions.size > 1) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clickable { expanded = !expanded },
                imageVector = if (expanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Hide dates" else "Show dates"
            )
        }
    }
}