package com.feature.weather.domain.repo

import com.feature.weather.domain.model.Forecastday
import com.feature.weather.domain.model.WeatherReportDataModel

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.domain.repo
 * @date 25-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

interface ForecastRepo {
    suspend fun getForecastData(
        forecastId: Long
    ): Forecastday

    suspend fun getAllForecastData(): ArrayList<Forecastday>
}