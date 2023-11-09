package com.feature.weather.domain.repo

import com.feature.weather.domain.model.WeatherReportDataModel

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.domain.repo
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

interface WeatherReportDataRepo {
    suspend fun getWeatherReportData(
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ): WeatherReportDataModel
}