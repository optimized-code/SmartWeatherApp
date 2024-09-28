package com.optmizedcode.core.common.helpers

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optmizedcode.core.common.helpers
 * @date 28-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 *
 ***************************************************************
 */

@RunWith(value = Parameterized::class)
class DateTimeUtilsTestParametrized(
    private val hourOfTheDay: Int,
    private val expectedResult: Boolean
) {

    @Test
    fun check_current_hour_isDay() {
        val result = DateTimeUtils.isDay(hourOfTheDay)
        assertEquals(expectedResult, result)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun inputs(): List<Array<Any>> {
            return listOf(
                arrayOf(0, false),
                arrayOf(1, false),
                arrayOf(2, false),
                arrayOf(3, false),
                arrayOf(4, false),
                arrayOf(5, true),
                arrayOf(6, true),
                arrayOf(7, true),
                arrayOf(8, true),
                arrayOf(9, true),
                arrayOf(10, true),
                arrayOf(11, true),
                arrayOf(12, true),
                arrayOf(13, true),
                arrayOf(14, true),
                arrayOf(15, true),
                arrayOf(16, true),
                arrayOf(17, true),
                arrayOf(18, false),
                arrayOf(19, false),
                arrayOf(20, false),
                arrayOf(21, false),
                arrayOf(22, false),
                arrayOf(23, false),
                arrayOf(24, true)
            )
        }

    }
}


class DateTimeUtilsTest {

    @Before
    fun setUp() {
        println("Execute before each test case")
    }

    @After
    fun clear() {
        println("Execute after each test case")
    }

    @Test
    fun check_hour5_isDay() {
        // Arrange
        // Act
        val result = DateTimeUtils.isDay(currentHour = 5)
        // Assert
        assertEquals(true, result)
    }

    @Test
    fun check_hour4_isDay() {
        // Arrange
        // Act
        val result = DateTimeUtils.isDay(4)
        // Assert
        assertEquals(false, result)
    }

    @Test
    fun check_hour19_isDay() {
        // Arrange
        // Act
        val result = DateTimeUtils.isDay(19)
        // Assert
        assertEquals(false, result)
    }

    @Test
    fun check_hour25_isDay() {
        // Arrange
        // Act
        val result = DateTimeUtils.isDay(25)
        // Assert
        assertEquals(true, result)
    }

}