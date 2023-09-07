package com.optmizedcode.smartweatherforcast.helpers

import android.app.Application

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.smartweatherforcast.helpers
 * @date 07-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPrefs.start(applicationContext)
    }
}