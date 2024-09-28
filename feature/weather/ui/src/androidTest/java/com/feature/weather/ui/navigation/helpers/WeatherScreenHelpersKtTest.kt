package com.feature.weather.ui.navigation.helpers

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

/**
 *
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.ui.navigation.helpers
 * @date 28-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 */

class WeatherScreenHelpersKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getWeatherIcon_fromCode_fromDay() {
        val result = getWeatherIcon(1003, false)
        assertEquals(com.optmizedcode.assets.R.drawable.ic_1003_n, result)
    }

    @Test
    fun getWeatherIcon_fromInvalidCode_fromDay() {
        val result = getWeatherIcon(1283, false)
        assertEquals(com.optmizedcode.assets.R.drawable.ic_1000_nn, result)
    }
}