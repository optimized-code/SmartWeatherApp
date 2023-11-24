package com.feature.weather.data.repo

import com.feature.weather.domain.model.Astro
import com.feature.weather.domain.model.Condition
import com.feature.weather.domain.model.Day
import com.feature.weather.domain.model.Forecastday
import com.feature.weather.domain.model.Hour
import com.feature.weather.domain.repo.ForecastRepo
import com.optimizedcode.database.dao.ForecastDao
import javax.inject.Inject

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.data.repo
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class ForecastRepoImpl @Inject constructor(
    private val forecastDao: ForecastDao
) :
    ForecastRepo {
    override suspend fun getForecastData(forecastId: Long): Forecastday {
        return Forecastday()
    }

    override suspend fun getAllForecastData(): ArrayList<Forecastday> {
        val completeForecast = forecastDao.getCompleteForecast()
        val result = arrayListOf<Forecastday>()
        completeForecast.forEach {
            val hours = arrayListOf<Hour>()
            it.hours.forEach { hour ->
                hours.add(
                    Hour(
                        chanceOfRain = hour.chanceOfRain,
                        chanceOfSnow = hour.chanceOfSnow,
                        cloud = hour.cloud,
                        condition = Condition(
                            text = hour.condition?.text,
                            icon = hour.condition?.icon,
                            code = hour.condition?.code
                        ),
                        dewpointC = hour.dewpointC,
                        dewpointF = hour.dewpointF,
                        feelslikeC = hour.feelslikeC,
                        feelslikeF = hour.feelslikeF,
                        gustKph = hour.gustKph,
                        gustMph = hour.gustMph,
                        heatindexC = hour.heatindexC,
                        heatindexF = hour.heatindexF,
                        humidity = hour.humidity,
                        isDay = hour.isDay,
                        precipIn = hour.precipIn,
                        precipMm = hour.precipMm,
                        pressureIn = hour.pressureIn,
                        pressureMb = hour.pressureMb,
                        tempC = hour.tempC,
                        tempF = hour.tempF,
                        time = hour.time,
                        timeEpoch = hour.timeEpoch,
                        uv = hour.uv,
                        visKm = hour.visKm,
                        visMiles = hour.visMiles,
                        willItRain = hour.willItRain,
                        willItSnow = hour.willItSnow,
                        windDegree = hour.windDegree,
                        windDir = hour.windDir,
                        windKph = hour.windKph,
                        windMph = hour.windMph,
                        windchillC = hour.windchillC,
                        windchillF = hour.windchillF
                    )
                )
            }

            val forecastDay = Forecastday(
                date = it.forecast.date,
                date_epoch = it.forecast.dateEpoch,
                day = Day(
                    maxtemp_c = it.forecast.day?.maxtemp_c,
                    maxtemp_f = it.forecast.day?.maxtemp_f,
                    mintemp_c = it.forecast.day?.mintemp_c,
                    mintemp_f = it.forecast.day?.mintemp_f,
                    avgtemp_c = it.forecast.day?.avgtemp_c,
                    avgtemp_f = it.forecast.day?.avgtemp_f,
                    maxwind_mph = it.forecast.day?.maxwind_mph,
                    maxwind_kph = it.forecast.day?.maxwind_kph,
                    totalprecip_mm = it.forecast.day?.totalprecip_mm,
                    totalprecip_in = it.forecast.day?.totalprecip_in,
                    totalsnow_cm = it.forecast.day?.totalsnow_cm,
                    avgvis_km = it.forecast.day?.avgvis_km,
                    avgvis_miles = it.forecast.day?.avgvis_miles,
                    avghumidity = it.forecast.day?.avghumidity,
                    daily_will_it_rain = it.forecast.day?.daily_will_it_rain,
                    daily_chance_of_rain = it.forecast.day?.daily_chance_of_rain,
                    daily_will_it_snow = it.forecast.day?.daily_will_it_snow,
                    daily_chance_of_snow = it.forecast.day?.daily_chance_of_snow,
                    condition = Condition(
                        text = it.forecast.day?.condition?.text,
                        icon = it.forecast.day?.condition?.icon,
                        code = it.forecast.day?.condition?.code
                    ),
                    uv = it.forecast.day?.uv

                ),
                astro = Astro(
                    sunrise = it.forecast.astro?.sunrise,
                    sunset = it.forecast.astro?.sunset,
                    moonrise = it.forecast.astro?.moonrise,
                    moonset = it.forecast.astro?.moonset,
                    moon_phase = it.forecast.astro?.moon_phase,
                    moon_illumination = it.forecast.astro?.moon_illumination,
                    is_moon_up = it.forecast.astro?.is_moon_up,
                    is_sun_up = it.forecast.astro?.is_sun_up
                ),
                hour = hours
            )

            result.add(forecastDay)
        }
        return result
    }

}