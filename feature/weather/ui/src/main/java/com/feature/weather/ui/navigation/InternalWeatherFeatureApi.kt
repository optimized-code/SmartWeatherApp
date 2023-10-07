package com.feature.weather.ui.navigation

import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.feature_api.FeatureApi
import com.optmizedcode.core.common.navigation_constant.WeatherFeature

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation
 * @date 18-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

internal object InternalWeatherFeatureApi: FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder
    ) {
        navGraphBuilder.navigation(startDestination = WeatherFeature.weatherScreenRoute, route = WeatherFeature.nestedRoute){
            composable(WeatherFeature.weatherScreenRoute){

            }
        }
    }

}