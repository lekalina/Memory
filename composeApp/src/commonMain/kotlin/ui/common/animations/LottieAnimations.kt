package ui.common.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
//import org.jetbrains.skia.Rect
//import org.jetbrains.skia.skottie.Animation
//import org.jetbrains.skia.sksg.InvalidationController
//import ui.common.lottieStrings.CELEBRATE_LOTTIE
import kotlin.math.roundToInt

@Composable
fun CelebrationLottie() {
    //Testing(CELEBRATE_LOTTIE, Modifier.fillMaxSize())
}

/*@Composable
fun Testing(lottieData: String, modifier: Modifier) {
    val animation = Animation.makeFromString(lottieData)
    InfiniteAnimation(animation, modifier)
}

@Composable
fun InfiniteAnimation(animation: Animation, modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = animation.duration,
        animationSpec = infiniteRepeatable(
            animation = tween((animation.duration * 1000).roundToInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val invalidationController = remember { InvalidationController() }

    /*
     FIXME: https://github.com/JetBrains/compose-multiplatform/issues/3149
      Animation type doesn't trigger re-drawing the canvas because of incorrect detection
      "stability" of external types.
      Adding _any_ mutable state into `drawIntoCanvas` scope resolves the issue.

      Workaround for iOS/Web: move this line into `drawIntoCanvas` block.
     */
    Canvas(modifier) {
        drawIntoCanvas {
            animation.render(
                canvas = it.nativeCanvas,
                dst = Rect.makeWH(size.width, size.height)
            )
            animation.seekFrameTime(time, invalidationController)
        }
    }
}*/