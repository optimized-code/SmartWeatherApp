package com.optmizedcode.core.network

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network
 * @date 09-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

object Endpoints {
    val API_KEY = BuildConfig.WEATHER_API_KEY

    //https://api.weatherapi.com/v1/forecast.json?key=384676e3678a47cfbe6173913230109&q=Jeddah&days=10&aqi=no&alerts=no
    const val WEATHER_REPORT = "forecast.json"
}