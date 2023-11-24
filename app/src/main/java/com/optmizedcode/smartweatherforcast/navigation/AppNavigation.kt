package com.optmizedcode.smartweatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.optmizedcode.core.common.navigation_constant.WeatherFeature

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.smartweatherforcast.navigation
 * @date 07-Oct-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(
        navController = navController,
        startDestination = WeatherFeature.weatherReportRoute
    ) {
        navigationProvider.weatherApi.registerGraph(navController, this)
    }
}