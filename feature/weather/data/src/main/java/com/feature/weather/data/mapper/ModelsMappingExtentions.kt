package com.feature.weather.data.mapper

import com.feature.weather.domain.model.AirQuality
import com.feature.weather.domain.model.Condition
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Location
import com.feature.weather.domain.model.WeatherReportDataModel
import com.optmizedcode.core.network.models.WeatherReportResponse

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.data.mapper
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

fun WeatherReportResponse.toDomainWeatherReportDataModel(): WeatherReportDataModel {
    return WeatherReportDataModel(
        Location(
            this.location.name,
            this.location.region,
            this.location.country,
            this.location.lat,
            this.location.lon,
            this.location.tzId,
            this.location.localtimeEpoch,
            this.location.localtime
        ),
        Current(
            this.current.lastUpdatedEpoch,
            this.current.lastUpdated,
            this.current.tempC,
            this.current.tempF,
            this.current.isDay,
            Condition(
                this.current.condition?.text,
                this.current.condition?.icon,
                this.current.condition?.code
            ),
            this.current.windMph,
            this.current.windKph,
            this.current.windDegree,
            this.current.windDir,
            this.current.pressureMb,
            this.current.pressureIn,
            this.current.precipMm,
            this.current.precipIn,
            this.current.humidity,
            this.current.cloud,
            this.current.feelslikeC,
            this.current.feelslikeF,
            this.current.visKm,
            this.current.visMiles,
            this.current.uv,
            this.current.gustMph,
            this.current.gustKph,
            AirQuality(
                this.current.airQuality?.co,
                this.current.airQuality?.no2,
                this.current.airQuality?.o3,
                this.current.airQuality?.so2,
                this.current.airQuality?.pm25
            )
        )
    )
}