package com.feature.weather.data.repo

import com.feature.weather.data.mapper.toDomainWeatherReportDataModel
import com.feature.weather.domain.model.WeatherReportDataModel
import com.feature.weather.domain.repo.WeatherReportDataRepo
import com.optmizedcode.core.network.dataproviders.WeatherDataProviders
import javax.inject.Inject

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.data.repo
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

class WeatherReportDataRepoImpl @Inject constructor(private val weatherDataProviders: WeatherDataProviders) :
    WeatherReportDataRepo {
    override suspend fun getWeatherReportData(
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ): WeatherReportDataModel {
        return weatherDataProviders.getWeatherReportData(city, days, aqi, alerts).toDomainWeatherReportDataModel()
    }
}