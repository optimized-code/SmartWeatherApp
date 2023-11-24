package com.feature.weather.ui.navigation.screen.forecastScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.weather.domain.use_cases.ForecastDataUseCase
import com.feature.weather.ui.navigation.screen.ForecastStateHolder
import com.optmizedcode.core.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.ui.navigation.screen.forecastScreen
 * @date 24-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val forecastDataUseCase: ForecastDataUseCase
) : ViewModel() {
    init {
        getForecastData()
    }

    var forecastData by mutableStateOf(ForecastStateHolder())
        private set

    private fun getForecastData() = viewModelScope.launch {
        forecastDataUseCase().onEach {
            forecastData = when (it) {
                is UiEvent.Loading -> {
                    ForecastStateHolder(true)
                }

                is UiEvent.Error -> {
                    ForecastStateHolder(error = it.message)
                }

                is UiEvent.Success -> {
                    ForecastStateHolder(success = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}