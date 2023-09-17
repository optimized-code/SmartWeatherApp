package com.feature.data.repo

import com.feature.data.mapper.toDomainWeatherReportDataModel
import com.feature.domain.model.WeatherReportDataModel
import com.feature.domain.repo.WeatherReportDataRepo
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
        key: String,
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ): WeatherReportDataModel {
        return weatherDataProviders.getWeatherReportData(key, city, days, aqi, alerts).toDomainWeatherReportDataModel()
    }
}