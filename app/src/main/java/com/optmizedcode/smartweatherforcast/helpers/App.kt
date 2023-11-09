package com.optmizedcode.smartweatherforcast.helpers

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.smartweatherforcast.helpers
 * @date 07-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPrefs.start(applicationContext)
    }
}