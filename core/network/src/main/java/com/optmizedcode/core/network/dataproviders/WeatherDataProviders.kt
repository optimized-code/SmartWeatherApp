package com.optmizedcode.core.network.dataproviders

import com.optmizedcode.core.network.ApiService
import javax.inject.Inject

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.dataproviders
 * @date 10-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

class WeatherDataProviders @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeatherReportData(
        key: String, city: String, days: Int, aqi: String, alerts: String
    ) = apiService.getWeatherReportData(key, city, days, aqi, alerts)
}