package com.feature.weather.data.mapper

import com.feature.weather.domain.model.AirQuality
import com.feature.weather.domain.model.Astro
import com.feature.weather.domain.model.Condition
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Day
import com.feature.weather.domain.model.Forecastday
import com.feature.weather.domain.model.Hour
import com.feature.weather.domain.model.Location
import com.feature.weather.domain.model.WeatherReportDataModel
import com.optmizedcode.core.network.models.WeatherReportResponse

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.data.mapper
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

fun WeatherReportResponse.toDomainWeatherReportDataModel(): WeatherReportDataModel {
    fun getHours(hours: ArrayList<com.optmizedcode.core.network.models.Hour>?): ArrayList<Hour>? {
        val result: ArrayList<Hour> = arrayListOf()
        hours?.forEach {
            val hour = Hour(
                chanceOfRain = it.chanceOfRain,
                chanceOfSnow = it.chanceOfSnow,
                cloud = it.cloud,
                condition = Condition(
                    text = it.condition?.text,
                    icon = it.condition?.icon,
                    code = it.condition?.code
                ),
                dewpointC = it.dewpointC,
                dewpointF = it.dewpointF,
                feelslikeC = it.feelslikeC,
                feelslikeF = it.feelslikeF,
                gustKph = it.gustKph,
                gustMph = it.gustMph,
                heatindexC = it.heatindexC,
                heatindexF = it.heatindexF,
                humidity = it.humidity,
                isDay = it.isDay,
                precipIn = it.precipIn,
                precipMm = it.precipMm,
                pressureIn = it.pressureIn,
                pressureMb = it.pressureMb,
                tempC = it.tempC,
                tempF = it.tempF,
                time = it.time,
                timeEpoch = it.timeEpoch,
                uv = it.uv,
                visKm = it.visKm,
                visMiles = it.visMiles,
                willItRain = it.willItRain,
                willItSnow = it.willItSnow,
                windDegree = it.windDegree,
                windDir = it.windDir,
                windKph = it.windKph,
                windMph = it.windMph,
                windchillC = it.windchillC,
                windchillF = it.windchillF
            )
            result.add(hour)
        }

        return result
    }

    fun getForecastDayList(forecast: ArrayList<com.optmizedcode.core.network.models.Forecastday>?): ArrayList<Forecastday> {
        val result: ArrayList<Forecastday> = arrayListOf()
        forecast?.forEach {
            val date: String? = it.date
            val date_epoch: Long? = it.date_epoch
            val day = if (it.day != null) {
                Day(
                    maxtemp_c = it.day?.maxtemp_c,
                    maxtemp_f = it.day?.maxtemp_f,
                    mintemp_c = it.day?.mintemp_c,
                    mintemp_f = it.day?.mintemp_f,
                    avgtemp_c = it.day?.avgtemp_c,
                    avgtemp_f = it.day?.avgtemp_f,
                    maxwind_mph = it.day?.maxwind_mph,
                    maxwind_kph = it.day?.maxwind_kph,
                    totalprecip_mm = it.day?.totalprecip_mm,
                    totalprecip_in = it.day?.totalprecip_in,
                    totalsnow_cm = it.day?.totalsnow_cm,
                    avgvis_km = it.day?.avgvis_km,
                    avgvis_miles = it.day?.avgvis_miles,
                    avghumidity = it.day?.avghumidity,
                    daily_will_it_rain = it.day?.daily_will_it_rain,
                    daily_chance_of_rain = it.day?.daily_chance_of_rain,
                    daily_will_it_snow = it.day?.daily_will_it_snow,
                    daily_chance_of_snow = it.day?.daily_chance_of_snow,
                    condition = Condition(
                        text = it.day?.condition?.text,
                        icon = it.day?.condition?.icon,
                        code = it.day?.condition?.code
                    ),
                    uv = it.day?.uv
                )
            } else {
                null
            }

            val astro = if (it.astro != null) {
                Astro(
                    sunrise = it.astro?.sunrise,
                    sunset = it.astro?.sunset,
                    moonrise = it.astro?.moonrise,
                    moonset = it.astro?.moonset,
                    moon_phase = it.astro?.moon_phase,
                    moon_illumination = it.astro?.moon_illumination,
                    is_moon_up = it.astro?.is_moon_up,
                    is_sun_up = it.astro?.is_sun_up
                )
            } else {
                null
            }

            val hour: ArrayList<Hour>? = getHours(it.hour)

            val eachDay = Forecastday(
                date = date,
                date_epoch = date_epoch,
                day = day,
                astro = astro,
                hour = hour
            )
            result.add(eachDay)
        }
        return result
    }

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
        ),
        forecast = getForecastDayList(this.forecast.forecastday)
    )
}