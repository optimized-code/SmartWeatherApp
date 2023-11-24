package com.feature.weather.domain.model

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.domain.model
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

data class WeatherReportDataModel(
    val location: Location,
    val current: Current,
    val forecast: ArrayList<Forecastday>
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

data class Forecastday(
    var date: String? = null,
    var date_epoch: Long? = null,
    var day: Day? = null,
    var astro: Astro? = null,
    var hour: ArrayList<Hour>? = null
)

data class Day(
    var maxtemp_c: Double? = null,
    var maxtemp_f: Double? = null,
    var mintemp_c: Double? = null,
    var mintemp_f: Double? = null,
    var avgtemp_c: Double? = null,
    var avgtemp_f: Double? = null,
    var maxwind_mph: Double? = null,
    var maxwind_kph: Double? = null,
    var totalprecip_mm: Double? = null,
    var totalprecip_in: Double? = null,
    var totalsnow_cm: Double? = null,
    var avgvis_km: Double? = null,
    var avgvis_miles: Double? = null,
    var avghumidity: Double? = null,
    var daily_will_it_rain: Double? = null,
    var daily_chance_of_rain: Double? = null,
    var daily_will_it_snow: Double? = null,
    var daily_chance_of_snow: Double? = null,
    var condition: Condition? = null,
    var uv: Double? = null,
)

data class Astro(
    var sunrise: String? = null,
    var sunset: String? = null,
    var moonrise: String? = null,
    var moonset: String? = null,
    var moon_phase: String? = null,
    var moon_illumination: String? = null,
    var is_moon_up: Boolean? = null,
    var is_sun_up: Boolean? = null,
)

data class Hour(
    var chanceOfRain: Long? = null,
    val chanceOfSnow: Long? = null,
    val cloud: Long? = null,
    val condition: Condition? = null,
    val dewpointC: Double? = null,
    val dewpointF: Double? = null,
    val feelslikeC: Double? = null,
    val feelslikeF: Double? = null,
    val gustKph: Double? = null,
    val gustMph: Double? = null,
    val heatindexC: Double? = null,
    val heatindexF: Double? = null,
    val humidity: Long? = null,
    val isDay: Long? = null,
    val precipIn: Double? = null,
    val precipMm: Double? = null,
    val pressureIn: Double? = null,
    val pressureMb: Double? = null,
    val tempC: Double? = null,
    val tempF: Double? = null,
    val time: String? = null,
    val timeEpoch: Long? = null,
    val uv: Double? = null,
    val visKm: Double? = null,
    val visMiles: Double? = null,
    val willItRain: Long? = null,
    val willItSnow: Long? = null,
    val windDegree: Long? = null,
    val windDir: String? = null,
    val windKph: Double? = null,
    val windMph: Double? = null,
    val windchillC: Double? = null,
    val windchillF: Double? = null
)