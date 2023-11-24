package com.feature.weather.ui.navigation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.feature.weather.ui.R

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.common_composables
 * @date 10-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Composable
fun gradientBg(): Brush {
    val colorStops = arrayOf(
        0.2f to MaterialTheme.colorScheme.surface,
        1f to colorResource(id = R.color.wa_secondary)
    )
    return Brush.linearGradient(
        colorStops = colorStops,
    )
}

@Composable
fun gradientBg1(): Brush {
    val colorStops = arrayOf(
        0.1f to colorResource(id = R.color.wa_lightBlue),
        1f to colorResource(id = R.color.wa_lightBlueVariant)
    )
    return Brush.verticalGradient (
        colorStops = colorStops,
    )
}

@Composable
fun summaryCircleGradientBg(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.wa_secondary),
            colorResource(id = R.color.purple_200)
        )
    )
}

@Composable
fun TemperatureGradientBg(
    firstVal: Float = 0.3f,
    secondVal: Float = 0.8f
): Brush {
    val colorStops = arrayOf(
        firstVal to colorResource(id = R.color.white),
        secondVal to colorResource(id = R.color.wa_lightBlue),
    )
    return Brush.verticalGradient (
        colorStops = colorStops,
    )
}

@Composable
fun WhiteRoundedCornerBackground(
    modifier: Modifier
) {
    Spacer( // white rounded corner box in the background
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStartPercent = 7, topEndPercent = 7))
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    )
}