package com.feature.weather.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.feature_api.FeatureApi
import com.feature.weather.ui.navigation.screen.forecastScreen.ForecastScreenViewModel
import com.feature.weather.ui.navigation.screen.homeScreen.InitWeatherReportScreen
import com.feature.weather.ui.navigation.screen.forecastScreen.NextDaysForecastScreen
import com.optmizedcode.core.common.navigation_constant.WeatherFeature

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

internal object InternalWeatherFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = WeatherFeature.weatherReportScreen,
            route = WeatherFeature.weatherReportRoute
        ) {
            composable(WeatherFeature.weatherReportScreen) {
                InitWeatherReportScreen(
                    onNextDaysForecastClick = {
                        navController.navigate(WeatherFeature.nextDaysForecastScreen)
                    }
                )
            }

            composable(WeatherFeature.nextDaysForecastScreen) {
                val viewModel = hiltViewModel<ForecastScreenViewModel>()
                NextDaysForecastScreen(viewModel)
            }
        }
    }

}