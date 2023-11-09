package com.feature.weather.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation
 * @date 18-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

interface WeatherApi : FeatureApi {}

class WeatherApiImpl : WeatherApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalWeatherFeatureApi.registerGraph(
            navController,
            navGraphBuilder
        )
    }

}