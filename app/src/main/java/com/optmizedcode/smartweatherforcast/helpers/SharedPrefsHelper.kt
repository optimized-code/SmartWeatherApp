package com.optmizedcode.smartweatherforcast.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Parcelable

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.smartweatherforcast.helpers
 * @date 02-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

enum class PREF_TYPE {
    USER,
    CONFIG,
    OTHER
}

object AppPrefs {

    private const val USER_PREFS = "user_prefs"
    private const val CONFIG_PREFS = "config_prefs"
    private const val OTHER_PREFS = "other_prefs"

    private lateinit var userPrefs: SharedPreferences
    private lateinit var configPrefs: SharedPreferences
    private lateinit var otherPrefs: SharedPreferences

    fun start(context: Context) {
        userPrefs = context.getSharedPreferences(USER_PREFS, MODE_PRIVATE)
        configPrefs = context.getSharedPreferences(CONFIG_PREFS, MODE_PRIVATE)
        otherPrefs = context.getSharedPreferences(OTHER_PREFS, MODE_PRIVATE)
    }

    fun whichPrefs(type: PREF_TYPE): SharedPreferences {
        return when (type) {
            PREF_TYPE.USER -> userPrefs
            PREF_TYPE.CONFIG -> configPrefs
            PREF_TYPE.OTHER -> otherPrefs
        }
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    fun set(key: String, value: Any?, type: PREF_TYPE) = run {
        when (value) {
            is String -> whichPrefs(type).edit { it.putString(key, value) }
            is Int -> whichPrefs(type).edit { it.putInt(key, value) }
            is Float -> whichPrefs(type).edit { it.putFloat(key, value) }
            is Boolean -> whichPrefs(type).edit { it.putBoolean(key, value) }
            is Long -> whichPrefs(type).edit { it.putLong(key, value) }
        }
    }

    inline fun <reified T : Any> get(key: String, type: PREF_TYPE): T =
        when (T::class) {
            String::class -> whichPrefs(type).getString(key, "") as T
            Int::class -> whichPrefs(type).getInt(key, 0) as T
            Float::class -> whichPrefs(type).getFloat(key, 0f) as T
            Boolean::class -> whichPrefs(type).getBoolean(key, false) as T
            Long::class -> whichPrefs(type).getLong(key, 0L) as T
            else -> throw UnsupportedOperationException("Still implementing")
        }


    fun clear(list: List<PREF_TYPE>) {
        list.forEach { pref ->
            whichPrefs(pref).edit { it.clear().apply() }
        }
    }

    fun remove(key: String, type: PREF_TYPE) {
        whichPrefs(type).edit { it.remove(key) }
    }
}