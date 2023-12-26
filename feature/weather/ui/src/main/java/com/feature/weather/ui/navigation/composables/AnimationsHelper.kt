package com.feature.weather.ui.navigation.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

fun GraphicsLayerScope.animateLayer(
    isAnimatable:Boolean,
    animationType: IconAnimationType,
    value: Float
){
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
    translationY = if (isAnimatable && animationType == IconAnimationType.TRANSLATION_Y) value else { 0f }
    translationX =
        if (isAnimatable && animationType == IconAnimationType.TRANSLATION_X) value else { 0f }

}
