package com.feature.weather.ui.navigation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
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
fun summaryCircleGradientBg(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.wa_secondary),
            colorResource(id = R.color.purple_200)
        )
    )
}