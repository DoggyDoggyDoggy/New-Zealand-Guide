package denys.diomaxius.newzealandguide.ui.screen.onboarding.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class RainDropData(val x: Float, val y: Float, val speed: Float, val alpha: Float)

@Composable
fun RealisticRainAnimation() {
    // Генерируем данные о каплях один раз
    val rainDrops = remember {
        List(100) {
            // x, y, скорость (множитель), прозрачность
            RainDropData(
                x = Math.random().toFloat(),
                y = Math.random().toFloat(),
                speed = 0.5f + Math.random().toFloat(), // Разная скорость падения
                alpha = 0.1f + Math.random().toFloat() * 0.4f
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        rainDrops.forEach { drop ->
            // Применяем индивидуальную скорость каждой капле
            val currentY = ((drop.y + progress * drop.speed) % 1f) * size.height
            val currentX = drop.x * size.width

            // Наклон (ветер) - добавляем 5-10 пикселей к X в конце линии
            val windOffset = 10f

            drawLine(
                color = Color.Blue.copy(alpha = drop.alpha),
                start = Offset(currentX, currentY),
                end = Offset(currentX + windOffset, currentY + 35f),
                strokeWidth = 4f * drop.speed // Чем быстрее капля, тем она "толще"
            )
        }
    }
}