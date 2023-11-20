package com.feature.weather.ui.navigation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.weather.domain.use_cases.GetWeatherReportDataUseCase
import com.optmizedcode.core.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Timer
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.concurrent.schedule

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@HiltViewModel
class TodayWeatherReportViewModel @Inject constructor(
    private val getWeatherReportDataUseCase: GetWeatherReportDataUseCase
) : ViewModel() {
    private val timer: Timer = Timer("CheckCurrentHour", true)

    init {
        getWeatherReport()
    }

    var currentHour by mutableIntStateOf(getCalenderCurrentHour())
        private set

    var weatherReportData by mutableStateOf(WeatherReportStateHolder())
        private set

    private fun getWeatherReport() = viewModelScope.launch {
        getWeatherReportDataUseCase("Jeddah", 7, "no", "no")
            .onEach {
                weatherReportData = when (it){
                    is UiEvent.Loading -> {
                        WeatherReportStateHolder(isLoading = true)
                    }
                    is UiEvent.Error -> {
                        WeatherReportStateHolder(error = it.message)
                    }
                    is UiEvent.Success -> {
                        WeatherReportStateHolder(success = it.data)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getCalenderCurrentHour(): Int {
        val calender: Calendar = Calendar.getInstance()
        return calender.get(Calendar.HOUR_OF_DAY)
    }

    fun checkHourChange() {
        // get remaining minutes and run after that
        timer.schedule(TimeUnit.MINUTES.toMillis(1)) {
            val hour = getCalenderCurrentHour()
            if (currentHour != hour){
                currentHour = hour
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}