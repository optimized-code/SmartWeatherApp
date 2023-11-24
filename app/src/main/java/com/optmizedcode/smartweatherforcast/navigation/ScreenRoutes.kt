package com.optmizedcode.smartweatherforcast.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.optmizedcode.core.common.navigation_constant.WeatherFeature
import com.optmizedcode.smartweatherforcast.R

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optmizedcode.smartweatherforcast.navigation
 * @date 14-Dec-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

sealed class ScreenRoutes(
    val route: String,
    val title: Any,
    val icon: Any
) {
    data object WeatherReportScreen : ScreenRoutes(
        route = WeatherFeature.weatherReportScreen,
        title = "",
        icon = Icons.Default.Home
    )

    data object SearchCityScreen : ScreenRoutes(
        route = WeatherFeature.searchCityScreen,
        title = "",
        icon = Icons.Default.Search
    )

    data object FavoritesScreen : ScreenRoutes(
        route = WeatherFeature.favoritesScreen,
        title = R.string.saved_cities,
        icon = Icons.Default.Favorite
    )

    data object SettingsScreen : ScreenRoutes(
        route = WeatherFeature.settingsScreen,
        title = R.string.settings,
        icon = Icons.Default.Face
    )
}