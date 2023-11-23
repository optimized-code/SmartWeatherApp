package com.feature.weather.ui.navigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.ui.navigation.screen
 * @date 23-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Composable
fun NextDaysForecastScreen() {
    Box(
        modifier = Modifier.background(Color.Red).fillMaxSize()
    ) {
        Text(
            text = "This is next day forecast screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
} 