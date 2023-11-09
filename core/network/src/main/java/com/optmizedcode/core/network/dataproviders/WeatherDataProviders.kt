package com.optmizedcode.core.network.dataproviders

import com.optmizedcode.core.network.ApiService
import com.optmizedcode.core.network.Endpoints.API_KEY
import javax.inject.Inject

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.dataproviders
 * @date 10-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class WeatherDataProviders @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeatherReportData(
        city: String, days: Int, aqi: String, alerts: String
    ) = apiService.getWeatherReportData(API_KEY, city, days, aqi, alerts)
}