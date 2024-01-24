package ui.common.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@Composable
fun FallingItemsAnimation(
    items: List<FallingItem>
) {
    val infiniteTransition = rememberInfiniteTransition(label = "snowflake_animation")
    items.forEach { item ->
        val animatedY by infiniteTransition.animateFloat(
            initialValue = item.yPosition,
            targetValue = item.yBottom,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = item.driftSpeed.toInt(), easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = ""
        )
        FallingItemComposable(
            item = item,
            modifier = Modifier.offset(x = item.xPosition.dp, y = animatedY.dp)
        )
    }
}

data class FallingItem(
    val id: String,
    var xPosition: Float,
    var yPosition: Float = 0f,
    var yBottom: Float = 1000f,
    val size: Dp = 25.dp,
    val driftSpeed: Float,
    val rotationAngle: Float,
    val rotationSpeed: Float,
    val isLottie: Boolean = false,
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FallingItemComposable(
    item: FallingItem,
    modifier: Modifier = Modifier,
) {
    Image(
        painterResource(res = item.id),
        modifier = modifier,
        contentDescription = null,
    )
}

@Composable
fun PreviewSnowfallAnimation() {
        val height = 100f
        val coins = listOf(
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
            "andy.xml",
        )
        val itemBoxSize = 15f

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp,
                top = 16.dp, bottom = 16.dp
            )
            .clickable { },
            backgroundColor = MaterialTheme.colors.primaryVariant,
            elevation = 0.dp
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                FallingObjectsAnimation(
                    modifier = Modifier.align(Alignment.CenterStart),
                    resIds = coins,
                    isLottie = true,
                    minViewHeight = height,
                    itemBoxSize = itemBoxSize
                )
                FallingObjectsAnimation(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(-(20f.dp)),
                    resIds = coins.subList(0, 3),
                    isLottie = true,
                    minViewHeight = height,
                    itemBoxSize = itemBoxSize
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = -(60f.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Testing Falling Objects Animation",
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
}

/**
 * Animation for randomized falling objects
 *
 * Falling objects exist inside a box object
 *
 * @param modifier - modifier for the box the falling items exist within
 * @param minViewHeight - the height of the box, the items will fall to the bottom of the viewHeight
 *
 * They are positioned in the x-direction based on:
 * 1. The position of the item in the array
 * @param resIds - A list of vector drawable resource ID's (these are the falling objects)
 * 2. The itemBoxSize
 * @param itemBoxSize - which is the width allowed for a single falling object
 * 3. A randomized offset where the randomized offset falls between 0 and half
 * the width of the itemBoxSize.
 *
 * Note - This ensures each item is dispersed across the x-axis, but with a slightly
 * randomized position within their allotted itemBoxSize so they are not evenly spaced.
 * If you would like the items to overlap slightly, make the itemBoxSize smaller than the items
 *
 * If the object is a lottie animation the size, rotation angle, and rotation speed are randomized
 * @param isLottie - allows a lottie animation to be loaded in place of a simple image resource
 * @param minSize - smallest size the object can be
 * @param maxSize - largest size the object can be
 * @param minRotationSpeed - slowest rotation speed
 * @param maxRotationSpeed - fastest rotation speed
 *
 * They are positioned in the y-direction based on:
 * 1. Even or Odd position in the array
 * 2. Randomized negative offset so that they begin out of view and appear at staggered times
 * @param minYOffsetEven - configurable minimum offset in the y direction for even items in the list
 * @param maxYOffsetEven - configurable maximum offset in the y direction for even items in the list
 * @param minYOffsetOdd - configurable minimum offset in the y direction for odd items in the list
 * @param maxYOffsetOdd - configurable maximum offset in the y direction for odd items in the list
 *
 * Note - All y offset params are converted into negative values so they are initialized off view.
 * This gives the illusion that items are falling naturally and not all from the same top position
 * at once
 *
 * The drift speed is also randomized between even and odd positioned items for a natural falling \
 * effect.
 * @param minDriftSpeedEven - configurable minimum drift speed for even items in the list
 * @param maxDriftSpeedEven - configurable maximum drift speed for even items in the list
 * @param minDriftSpeedOdd - configurable minimum drift speed for odd items in the list
 * @param maxDriftSpeedOdd - configurable maximum drift speed for odd items in the list
 *
 */
@Composable
fun FallingObjectsAnimation(
    modifier: Modifier = Modifier,
    resIds: List<String>,
    minViewHeight: Float,
    minSize: Int = 20,
    maxSize: Int = 35,
    isLottie: Boolean = false,
    itemBoxSize: Float = 20f,
    minYOffsetEven: Int = 0,
    maxYOffsetEven: Int = 50,
    minYOffsetOdd: Int = 50,
    maxYOffsetOdd: Int = 100,
    minDriftSpeedEven: Int = 2500,
    maxDriftSpeedEven: Int = 3250,
    minDriftSpeedOdd: Int = 3250,
    maxDriftSpeedOdd: Int = 4000,
    minRotationSpeed: Int = 1,
    maxRotationSpeed: Int = 10,
    minRotationAngle: Int = 0,
    maxRotationAngle: Int = 180,
) {
    val viewWidth = itemBoxSize * resIds.size
    Box(modifier.defaultMinSize(minHeight = minViewHeight.dp, minWidth = viewWidth.dp)) {
        val fallingItems = resIds.mapIndexed { index, id ->
            // Add offset to starting positions (randomized so items are not evenly spaced)
            val xOffset = Random.nextInt(0, itemBoxSize.toInt() / 2)
            // Offset the y starting positions so they don't all appear in the box at the same time
            // (they are staggered for even and odds so no 2 will be next to each other on init
            val yStart = if (index % 2 == 0) Random.nextInt(
                from = minYOffsetEven, until = maxYOffsetEven
            ) else Random.nextInt(
                from = minYOffsetOdd, until = maxYOffsetOdd
            )
            // Set the drift speed so they fall at different rates (staggered by even odd for fall rates)
            val drift = if (index % 2 == 0) Random.nextInt(
                from = minDriftSpeedEven, until = maxDriftSpeedEven
            ) else Random.nextInt(
                from = minDriftSpeedOdd, until = maxDriftSpeedOdd
            )
            // Set randomized size and rotation angle & speed
            val size = if (index % 2 == 0) Random.nextInt(
                from = minSize, until = (maxSize - minSize) / 2 + minSize
            ) else Random.nextInt(
                from = (maxSize - minSize) / 2 + minSize, until = maxSize
            )
            val rotationAngle = Random.nextInt(minRotationAngle, maxRotationAngle)
            val rotationSpeed = Random.nextInt(minRotationSpeed, maxRotationSpeed)
            // xPosition calculated as the size of the item box with the margin offset
            val xPosition = index * itemBoxSize + xOffset
            FallingItem(
                id = id,
                size = size.dp,
                isLottie = isLottie,
                xPosition = xPosition,
                yPosition = -yStart.toFloat(),
                yBottom = minViewHeight,
                driftSpeed = drift.toFloat(),
                rotationAngle = rotationAngle.toFloat(),
                rotationSpeed = rotationSpeed.toFloat()
            )
        }
        FallingItemsAnimation(items = fallingItems)
    }
}
