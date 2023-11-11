package com.feature.weather.ui.navigation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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

    private val _currentHour = mutableIntStateOf(getCalenderCurrentHour())
    val currentHour: Int
        get() = _currentHour.intValue

    private val _weatherReportData = mutableStateOf(WeatherReportStateHolder())
    val weatherReportData: State<WeatherReportStateHolder>
        get() = _weatherReportData

    fun getWeatherReport() = viewModelScope.launch {
        getWeatherReportDataUseCase("Jeddah", 7, "no", "no")
            .onEach {
                when (it){
                    is UiEvent.Loading -> {
                        _weatherReportData.value = WeatherReportStateHolder(isLoading = true)
                    }
                    is UiEvent.Error -> {
                        _weatherReportData.value = WeatherReportStateHolder(error = it.message)
                    }
                    is UiEvent.Success -> {
                        _weatherReportData.value = WeatherReportStateHolder(success = it.data)
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
            if (_currentHour.intValue != hour){
                _currentHour.intValue = hour
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}