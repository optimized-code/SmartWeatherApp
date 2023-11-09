package com.feature.weather.ui.navigation.screen

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 09-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Preview(
    name = "Dark mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Preview(
    name = "Light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
annotation class DarkLightPreviews

@Preview(name = "5-inch Device Landscape", widthDp = 640, heightDp = 360)
annotation class Landscape5Inch

@Preview(name = "5-inch Device Portrait", widthDp = 360, heightDp = 640)
annotation class Portrait5Inch
