package com.feature.weather.ui.navigation.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.ui.navigation.composables
 * @date 26-Dec-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

enum class IconAnimationType {
    ROTATION,
    TRANSLATION_Y,
    TRANSLATION_X,
    TILT,
    SCALE_ANIM
}

@Composable
fun rotationAnimation(): Float {
    val rotationState = rememberInfiniteTransition("")
    val angle by rotationState.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing)
        ), label = ""
    )

    return angle
}

@Composable
fun breathingAnimation(): Float {
    val breathingState = rememberInfiniteTransition("")
    val translation by breathingState.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    return translation
}

@Composable
fun tiltAnimation(): Float {
    val tiltState = rememberInfiniteTransition("")
    val angle by tiltState.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    return angle
}

@Composable
fun scaleInOutAnimation(): Float {
    val tiltState = rememberInfiniteTransition("")
    val angle by tiltState.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    return angle
}

@Composable
fun KnockAnimation() {

}

fun Modifier.shimmerLoadingAnimation(
    isEnabled: Boolean = false,
    color: Color = Color.White,
    widthOfShadowBrush: Int = 200,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 2000,
): Modifier {
    if (isEnabled) {
        return composed {

            val shimmerColors = listOf(
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 1.0f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
            )

            val transition = rememberInfiniteTransition(label = "")

            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "Shimmer loading animation",
            )

            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
            )
        }
    }

    return this
}

//@Preview
//@Composable
//fun SparkleAnimation(modifier: Modifier = Modifier) {
//
//    val tiltState = rememberInfiniteTransition("")
//    val angle by tiltState.animateValue(
//        initialValue = -100f,
//        targetValue = 200f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart
//        ), label = ""
//    )
//
//    Box(modifier = modifier
//        .size(100.dp)
//        .graphicsLayer {
//            rotationZ = 40f
//            translationY = angle //-100f
//        }
//    ){
//        Spacer(
//            modifier = modifier
//                .fillMaxWidth()
//                .height(10.dp)
//                .background(Color.Yellow)
//        )
//    }
//}

fun GraphicsLayerScope.animateLayer(
    isAnimatable: Boolean,
    animationType: IconAnimationType,
    value: Float
) {
    val rotation = if (isAnimatable) {
        if (animationType == IconAnimationType.ROTATION || animationType == IconAnimationType.TILT) {
            value
        } else {
            0f
        }
    } else {
        0f
    }
    rotationZ = rotation
    translationY =
        if (isAnimatable && animationType == IconAnimationType.TRANSLATION_Y) value else {
            0f
        }
    translationX =
        if (isAnimatable && animationType == IconAnimationType.TRANSLATION_X) value else {
            0f
        }

}
