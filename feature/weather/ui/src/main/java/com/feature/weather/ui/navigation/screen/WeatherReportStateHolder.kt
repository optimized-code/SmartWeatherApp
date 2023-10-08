package com.feature.weather.ui.navigation.screen

import com.feature.weather.domain.model.WeatherReportDataModel

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

data class WeatherReportStateHolder(
    var isLoading: Boolean = false,
    var error: String = "",
    var success: WeatherReportDataModel? = null
)
