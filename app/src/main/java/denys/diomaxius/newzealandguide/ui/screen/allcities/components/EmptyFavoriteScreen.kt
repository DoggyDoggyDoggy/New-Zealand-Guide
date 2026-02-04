package denys.diomaxius.newzealandguide.ui.screen.allcities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.newzealandguide.R

@Composable
fun EmptyFavoriteScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(128.dp),
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.add_your_favorite_cities),
            fontSize = 24.sp,
            color = Color.Gray
        )
    }
}