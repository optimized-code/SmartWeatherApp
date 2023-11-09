package com.optmizedcode.core.network.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.models
 * @date 09-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherReportResponse(
    @JsonProperty("location") var location: Location = Location(),
    @JsonProperty("current") var current: Current = Current(),
    @JsonProperty("forecast") var forecast: Forecast = Forecast(),
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Location(
    @JsonProperty("name") var name: String? = null,
    @JsonProperty("region") var region: String? = null,
    @JsonProperty("country") var country: String? = null,
    @JsonProperty("lat") var lat: Double? = null,
    @JsonProperty("lon") var lon: Double? = null,
    @JsonProperty("tz_id") var tzId: String? = null,
    @JsonProperty("localtime_epoch") var localtimeEpoch: Int? = null,
    @JsonProperty("localtime") var localtime: String? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Current(
    @JsonProperty("last_updated_epoch") var lastUpdatedEpoch: Int? = null,
    @JsonProperty("last_updated") var lastUpdated: String? = null,
    @JsonProperty("temp_c") var tempC: Int? = null,
    @JsonProperty("temp_f") var tempF: Double? = null,
    @JsonProperty("is_day") var isDay: Int? = null,
    @JsonProperty("condition") var condition: Condition? = Condition(),
    @JsonProperty("wind_mph") var windMph: Double? = null,
    @JsonProperty("wind_kph") var windKph: Double? = null,
    @JsonProperty("wind_degree") var windDegree: Int? = null,
    @JsonProperty("wind_dir") var windDir: String? = null,
    @JsonProperty("pressure_mb") var pressureMb: Int? = null,
    @JsonProperty("pressure_in") var pressureIn: Double? = null,
    @JsonProperty("precip_mm") var precipMm: Int? = null,
    @JsonProperty("precip_in") var precipIn: Int? = null,
    @JsonProperty("humidity") var humidity: Int? = null,
    @JsonProperty("cloud") var cloud: Int? = null,
    @JsonProperty("feelslike_c") var feelslikeC: Double? = null,
    @JsonProperty("feelslike_f") var feelslikeF: Double? = null,
    @JsonProperty("vis_km") var visKm: Int? = null,
    @JsonProperty("vis_miles") var visMiles: Int? = null,
    @JsonProperty("uv") var uv: Int? = null,
    @JsonProperty("gust_mph") var gustMph: Int? = null,
    @JsonProperty("gust_kph") var gustKph: Double? = null,
    @JsonProperty("air_quality") var airQuality: AirQuality? = AirQuality()
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Condition(
    @JsonProperty("text") var text: String? = null,
    @JsonProperty("icon") var icon: String? = null,
    @JsonProperty("code") var code: Int? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AirQuality(
    @JsonProperty("co") var co: Double? = null,
    @JsonProperty("no2") var no2: Double? = null,
    @JsonProperty("o3") var o3: Double? = null,
    @JsonProperty("so2") var so2: Double? = null,
    @JsonProperty("pm2_5") var pm25: Double? = null,
    @JsonProperty("pm10") var pm10: Double? = null,
    @JsonProperty("us-epa-index") var usEpaIndex: Int? = null,
    @JsonProperty("gb-defra-index") var gbDefraIndex: Int? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Forecast(
    @JsonProperty("forecastday") var forecastday: ArrayList<Forecastday>? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Forecastday(
    @JsonProperty("date") var date: String? = null,
    @JsonProperty("date_epoch") var date_epoch: Long? = null,
    @JsonProperty("day") var day: Day? = null,
    @JsonProperty("astro") var astro: Astro? = null,
    @JsonProperty("hour") var hour: ArrayList<Hour>? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Day(
    @JsonProperty("maxtemp_c") var maxtemp_c: Double? = null,
    @JsonProperty("maxtemp_f") var maxtemp_f: Double? = null,
    @JsonProperty("mintemp_c") var mintemp_c: Double? = null,
    @JsonProperty("mintemp_f") var mintemp_f: Double? = null,
    @JsonProperty("avgtemp_c") var avgtemp_c: Double? = null,
    @JsonProperty("avgtemp_f") var avgtemp_f: Double? = null,
    @JsonProperty("maxwind_mph") var maxwind_mph: Double? = null,
    @JsonProperty("maxwind_kph") var maxwind_kph: Double? = null,
    @JsonProperty("totalprecip_mm") var totalprecip_mm: Double? = null,
    @JsonProperty("totalprecip_in") var totalprecip_in: Double? = null,
    @JsonProperty("totalsnow_cm") var totalsnow_cm: Double? = null,
    @JsonProperty("avgvis_km") var avgvis_km: Double? = null,
    @JsonProperty("avgvis_miles") var avgvis_miles: Double? = null,
    @JsonProperty("avghumidity") var avghumidity: Double? = null,
    @JsonProperty("daily_will_it_rain") var daily_will_it_rain: Double? = null,
    @JsonProperty("daily_chance_of_rain") var daily_chance_of_rain: Double? = null,
    @JsonProperty("daily_will_it_snow") var daily_will_it_snow: Double? = null,
    @JsonProperty("daily_chance_of_snow") var daily_chance_of_snow: Double? = null,
    @JsonProperty("condition") var condition: Condition? = null,
    @JsonProperty("uv") var uv: Double? = null,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Astro(
    @JsonProperty("sunrise") var sunrise: String? = null,
    @JsonProperty("sunset") var sunset: String? = null,
    @JsonProperty("moonrise") var moonrise: String? = null,
    @JsonProperty("moonset") var moonset: String? = null,
    @JsonProperty("moon_phase") var moon_phase: String? = null,
    @JsonProperty("moon_illumination") var moon_illumination: String? = null,
    @JsonProperty("is_moon_up") var is_moon_up: Boolean? = null,
    @JsonProperty("is_sun_up") var is_sun_up: Boolean? = null,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Hour(
    @JsonProperty("chance_of_rain") var chanceOfRain: Long? = null,
    @JsonProperty("chance_of_snow") val chanceOfSnow: Long? = null,
    @JsonProperty("cloud") val cloud: Long? = null,
    @JsonProperty("condition") val condition: Condition? = null,
    @JsonProperty("dewpoint_c") val dewpointC: Double? = null,
    @JsonProperty("dewpoint_f") val dewpointF: Double? = null,
    @JsonProperty("feelslike_c") val feelslikeC: Double? = null,
    @JsonProperty("feelslike_f") val feelslikeF: Double? = null,
    @JsonProperty("gust_kph") val gustKph: Double? = null,
    @JsonProperty("gust_mph") val gustMph: Double? = null,
    @JsonProperty("heatindex_c") val heatindexC: Double? = null,
    @JsonProperty("heatindex_f") val heatindexF: Double? = null,
    @JsonProperty("humidity") val humidity: Long? = null,
    @JsonProperty("is_day") val isDay: Long? = null,
    @JsonProperty("precip_in") val precipIn: Double? = null,
    @JsonProperty("precip_mm") val precipMm: Double? = null,
    @JsonProperty("pressure_in") val pressureIn: Double? = null,
    @JsonProperty("pressure_mb") val pressureMb: Double? = null,
    @JsonProperty("temp_c") val tempC: Double? = null,
    @JsonProperty("temp_f") val tempF: Double? = null,
    @JsonProperty("time") val time: String? = null,
    @JsonProperty("time_epoch") val timeEpoch: Long? = null,
    @JsonProperty("uv") val uv: Double? = null,
    @JsonProperty("vis_km") val visKm: Double? = null,
    @JsonProperty("vis_miles") val visMiles: Double? = null,
    @JsonProperty("will_it_rain") val willItRain: Long? = null,
    @JsonProperty("will_it_snow") val willItSnow: Long? = null,
    @JsonProperty("wind_degree") val windDegree: Long? = null,
    @JsonProperty("wind_dir") val windDir: String? = null,
    @JsonProperty("wind_kph") val windKph: Double? = null,
    @JsonProperty("wind_mph") val windMph: Double? = null,
    @JsonProperty("windchill_c") val windchillC: Double? = null,
    @JsonProperty("windchill_f") val windchillF: Double? = null
)