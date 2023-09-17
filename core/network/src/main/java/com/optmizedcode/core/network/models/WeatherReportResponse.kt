package com.optmizedcode.core.network.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.models
 * @date 09-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherReportResponse(
    @JsonProperty("location") var location: Location = Location(),
    @JsonProperty("current") var current: Current = Current(),
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

