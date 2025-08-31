package denys.diomaxius.newzealandguide.ui.screen.allcities

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Filters(
    modifier: Modifier = Modifier,
    showFavorite: Boolean,
    toggleFavorite: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        FilterChip(
            selected = !showFavorite,
            onClick = if (showFavorite) toggleFavorite else ({}),
            label = { Text(text = "All") }
        )

        Spacer(modifier = Modifier.width(12.dp))

        FilterChip(
            selected = showFavorite,
            onClick = if (!showFavorite) toggleFavorite else ({}),
            label = { Text(text = "Favorite") }
        )
    }
}