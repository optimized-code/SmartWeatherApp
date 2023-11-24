package com.feature.weather.data.repo

import com.feature.weather.data.mapper.toDomainWeatherReportDataModel
import com.feature.weather.domain.model.WeatherReportDataModel
import com.feature.weather.domain.repo.WeatherReportDataRepo
import com.optimizedcode.database.dao.AstroDao
import com.optimizedcode.database.dao.ConditionDao
import com.optimizedcode.database.dao.DayDao
import com.optimizedcode.database.dao.ForecastDao
import com.optimizedcode.database.dao.HourDao
import com.optimizedcode.database.entities.AstroEntity
import com.optimizedcode.database.entities.Condition
import com.optimizedcode.database.entities.ConditionEntity
import com.optimizedcode.database.entities.DayEntity
import com.optimizedcode.database.entities.ForecastEntity
import com.optimizedcode.database.entities.HourEntity
import com.optmizedcode.core.network.dataproviders.WeatherDataProviders
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

class WeatherReportDataRepoImpl @Inject constructor(
    private val weatherDataProviders: WeatherDataProviders,
    private val forecastDao: ForecastDao,
    private val hourDao: HourDao
) : WeatherReportDataRepo {
    override suspend fun getWeatherReportData(
        city: String, days: Int, aqi: String, alerts: String
    ): WeatherReportDataModel {
        val result = weatherDataProviders.getWeatherReportData(city, days, aqi, alerts)
            .toDomainWeatherReportDataModel()

        forecastDao.deleteAll()
        hourDao.deleteAll()

        result.forecast.forEach {
            val day = DayEntity(
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
                )
            )

            val astro = AstroEntity(
                sunrise = it.astro?.sunrise,
                sunset = it.astro?.sunset,
                moonrise = it.astro?.moonrise,
                moonset = it.astro?.moonset,
                moon_phase = it.astro?.moon_phase,
                moon_illumination = it.astro?.moon_illumination,
                is_moon_up = it.astro?.is_moon_up,
                is_sun_up = it.astro?.is_sun_up
            )

            // Insert forecast
            val forecastId = forecastDao.insert(
                ForecastEntity(
                    date = it.date,
                    dateEpoch = it.date_epoch,
                    day = day,
                    astro = astro
                )
            )

            // Now insert hours
            val hourList = arrayListOf<HourEntity>()
            it.hour?.forEach { hour ->
                hourList.add(
                    HourEntity(
                        forecast_id = forecastId,
                        chanceOfRain = hour.chanceOfRain,
                        chanceOfSnow = hour.chanceOfSnow,
                        cloud = hour.cloud,
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
                        windchillF = hour.windchillF,
                        condition = Condition(
                            text = hour.condition?.text,
                            icon = hour.condition?.icon,
                            code = hour.condition?.code
                        )
                    )
                )
            }
            hourDao.insertAll(hourList)
        }

        return result
    }
}