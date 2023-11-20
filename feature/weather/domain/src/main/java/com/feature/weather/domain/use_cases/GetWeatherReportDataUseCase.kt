package com.feature.weather.domain.use_cases

import com.feature.weather.domain.repo.WeatherReportDataRepo
import com.optmizedcode.core.common.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.domain.use_cases
 * @date 16-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class GetWeatherReportDataUseCase @Inject constructor(private val weatherReportDataRepo: WeatherReportDataRepo) {

    operator fun invoke(
        city: String,
        days: Int,
        aqi: String,
        alerts: String
    ) = flow {
        emit(UiEvent.Loading())
//        val data = WeatherReportDataModel(Location(), Current(), arrayListOf())
        val data = weatherReportDataRepo.getWeatherReportData(city, days, aqi, alerts)
        emit(UiEvent.Success(data))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}