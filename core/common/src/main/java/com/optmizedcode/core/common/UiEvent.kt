package com.optmizedcode.core.common

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.common
 * @date 16-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

sealed class UiEvent<T>(val data: T? = null, val message: String = "") {
    class Loading<T>(): UiEvent<T>()
    class Success<T>(data: T?): UiEvent<T>(data = data)
    class Error<T>(message: String): UiEvent<T>(message = message)
}
