/*
**************************************************************
 * www.optmizedcode.com
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.smartweatherforcast.helper
 * @date 08-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

package com.optmizedcode.smartweatherforcast.helper

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.clear
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.get
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.remove
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.set
import com.optmizedcode.smartweatherforcast.helpers.PREFSTYPE
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(value = AndroidJUnit4::class)
class AppPrefsInstrumentedTests {

    private lateinit var context: Context
    @Before
    fun start(){
        context = ApplicationProvider.getApplicationContext()
        AppPrefs.start(context)
    }

    @Test
    fun setAndGetValuesTest() {
        AppPrefs.prefs()["string_val"] = "abc"
        AppPrefs.prefs()["int_val"] = 1
        AppPrefs.prefs()["float_val"] = 20.0f
        AppPrefs.prefs()["boolean_val"] = false
        AppPrefs.prefs()["long_val"] = 10000000L

        AppPrefs.prefs().clear()
        AppPrefs.prefs().remove("long_val")
        assertEquals("abc", AppPrefs.prefs().get<String>("string_val"))
        assertEquals(1, AppPrefs.prefs().get<Int>("int_val"))
        assertEquals(20.0f, AppPrefs.prefs().get<Float>("float_val"))
        assertEquals(false, AppPrefs.prefs().get<Boolean>("boolean_val"))
        assertEquals(10000000L, AppPrefs.prefs()["long_val"])
    }

    @Test
    fun removeKeyTest() {
        AppPrefs.prefs()["string_val"] = "abc"
        AppPrefs.prefs().remove("string_val")
        assertEquals("abc", AppPrefs.prefs().get<String>("string_val"))
    }

    @Test
    fun clearSharedPrefsTest() {
        AppPrefs.prefs()["string_val"] = "abc"
        AppPrefs.prefs().clear()
        assertEquals("abc", AppPrefs.prefs().get<String>("string_val"))
    }

    @Test
    fun clearSpecificSharedPrefsTest() {
        AppPrefs.prefs(PREFSTYPE.CONFIG)["string_val"] = "abc"
        AppPrefs.prefs().clear(listOf(PREFSTYPE.CONFIG))
        assertEquals("abc", AppPrefs.prefs().get<String>("string_val"))
    }
}
