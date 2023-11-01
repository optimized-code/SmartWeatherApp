package com.feature.weather.ui.navigation.screen

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/*
**************************************************************
 * www.optimizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 31-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

sealed class UiText {
    data class StringValue(val str: String) : UiText()
    class StringResourceValue(@StringRes val str: Int, vararg val args: Any): UiText()

    @Composable
    fun getString(): String{
        return when(this){
            is StringValue -> {
                str
            }
            is StringResourceValue -> {
                stringResource(id = str, args)
            }
        }

    }
}
