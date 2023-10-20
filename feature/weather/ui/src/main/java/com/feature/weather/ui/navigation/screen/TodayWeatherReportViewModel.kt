package com.feature.weather.ui.navigation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.weather.domain.use_cases.GetWeatherReportDataUseCase
import com.optmizedcode.core.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@HiltViewModel
class TodayWeatherReportViewModel @Inject constructor(private val getWeatherReportDataUseCase: GetWeatherReportDataUseCase) :
    ViewModel() {

    init {
        getWeatherReport()
    }

    private val _weatherReportData = mutableStateOf(WeatherReportStateHolder())
    val weatherReportData: State<WeatherReportStateHolder>
        get() = _weatherReportData

    private fun getWeatherReport() = viewModelScope.launch {
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

}