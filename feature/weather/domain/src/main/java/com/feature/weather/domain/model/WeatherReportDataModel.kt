package com.feature.weather.domain.model

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.domain.model
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

data class WeatherReportDataModel(
    val location: Location,
    val current: Current
)

data class Location(
    var name: String? = null,
    var region: String? = null,
    var country: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var tzId: String? = null,
    var localtimeEpoch: Int? = null,
    var localtime: String? = null
)

data class Current(
    var lastUpdatedEpoch: Int? = null,
    var lastUpdated: String? = null,
    var tempC: Int? = null,
    var tempF: Double? = null,
    var isDay: Int? = null,
    var condition: Condition? = Condition(),
    var windMph: Double? = null,
    var windKph: Double? = null,
    var windDegree: Int? = null,
    var windDir: String? = null,
    var pressureMb: Int? = null,
    var pressureIn: Double? = null,
    var precipMm: Int? = null,
    var precipIn: Int? = null,
    var humidity: Int? = null,
    var cloud: Int? = null,
    var feelslikeC: Double? = null,
    var feelslikeF: Double? = null,
    var visKm: Int? = null,
    var visMiles: Int? = null,
    var uv: Int? = null,
    var gustMph: Int? = null,
    var gustKph: Double? = null,
    var airQuality: AirQuality? = AirQuality()
)

data class Condition(
    var text: String? = null,
    var icon: String? = null,
    var code: Int? = null
)

data class AirQuality(
    var co: Double? = null,
    var no2: Double? = null,
    var o3: Double? = null,
    var so2: Double? = null,
    var pm25: Double? = null,
    var pm10: Double? = null,
    var usEpaIndex: Int? = null,
    var gbDefraIndex: Int? = null
)
