package com.optmizedcode.core.network

import com.optmizedcode.core.network.Endpoints.API_KEY
import com.optmizedcode.core.network.models.WeatherReportResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network
 * @date 09-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class FakeRestApi(val fileName: String)

interface ApiService {

    @GET(Endpoints.WEATHER_REPORT)
    suspend fun getWeatherReportData(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ): WeatherReportResponse
}