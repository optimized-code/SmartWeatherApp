package com.feature.weather.ui.navigation.helpers

import com.feature.weather.domain.model.Forecastday
import com.feature.weather.ui.R
import com.feature.weather.ui.navigation.screen.UiText
import java.util.Calendar

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.helpers
 * @date 10-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

fun Int.toStrDay(): String{
    return when(this){
        1 -> "Sun"
        2 -> "Mon"
        3 -> "Tue"
        4 -> "Wed"
        5 -> "Thu"
        6 -> "Fri"
        7 -> "Sat"
        else ->
            "Noday"
    }
}

fun getDayFromDate(date: String): String {
    val calender: Calendar = Calendar.getInstance()
    val values = date.split("-")
    calender.set(Calendar.YEAR, values[0].toInt())
    calender.set(Calendar.MONTH, values[1].toInt())
    calender.set(Calendar.DAY_OF_MONTH, values[2].toInt())
    return calender.get(Calendar.DAY_OF_WEEK).toStrDay()
}

fun getPrecipitationMessage(days: ArrayList<Forecastday>): UiText {
    for (day in days){
        day.day?.let {
            val precipitation = it.totalprecip_mm ?: 0.0
            val date = day.date ?: "" // e.g 2023-11-03
            val args = arrayOf(precipitation.toString(), getDayFromDate(date))
            if (precipitation > 0.0){
                return UiText.StringResourceValue(R.string.expected_mm_rain_on, *args)
            }
        }
    }

    return UiText.StringResourceValue(R.string.no_rain_expected)
}

fun getUVIndexMessage(uvIndex: Int): UiText {
    return if (uvIndex <= 2){
        UiText.StringResourceValue(R.string.no_need_to_use_sun_protection)
    }
    else if (uvIndex in 3..5) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_2pm)
    }
    else if (uvIndex in 6..7) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_3pm)
    }
    else if (uvIndex in 8..10) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_4pm)
    }
    else if (uvIndex in 11..100) {
        UiText.StringResourceValue(R.string.avoid_direct_sunlight_interaction)
    }
    else {
        UiText.StringResourceValue(R.string.use_sun_protection_to_be_safe)
    }
}

fun getFeelsLikeMessage(actualTemp: Int, feelsLikeTemp: Int): UiText {
    return if (actualTemp == feelsLikeTemp){
        UiText.StringResourceValue(R.string.similar_to_the_actual_temperature)
    }
    else if (actualTemp > feelsLikeTemp){
        UiText.StringResourceValue(R.string.wind_is_making_it_cooler)
    }
    else {
        UiText.StringResourceValue(R.string.humidity_is_making_it_warmer)
    }
}

fun getUVIndexIntensity(uvIndex: Int): UiText {
    return if (uvIndex <= 2){
        UiText.StringResourceValue(R.string.low)
    }
    else if (uvIndex in 3..5) {
        UiText.StringResourceValue(R.string.moderate)
    }
    else if (uvIndex in 6..7) {
        UiText.StringResourceValue(R.string.high)
    }
    else if (uvIndex in 8..10) {
        UiText.StringResourceValue(R.string.very_high)
    }
    else if (uvIndex in 11..100) {
        UiText.StringResourceValue(R.string.extreme)
    }
    else {
        UiText.StringResourceValue(R.string.low)
    }
}

fun getWeatherIcon(code: Int?, isDay: Boolean): Int{
    return when (code) {
        1000 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1000 else com.optmizedcode.assets.R.drawable.ic_1000_nn
        1003 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1003 else com.optmizedcode.assets.R.drawable.ic_1003_n
        1006 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1006 else com.optmizedcode.assets.R.drawable.ic_1006_n
        1009 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1009 else com.optmizedcode.assets.R.drawable.ic_1009_n
        1030 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1030 else com.optmizedcode.assets.R.drawable.ic_1030_n
        1063 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1063 else com.optmizedcode.assets.R.drawable.ic_1063_n
        1066 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1066 else com.optmizedcode.assets.R.drawable.ic_1066_n
        1069 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1069 else com.optmizedcode.assets.R.drawable.ic_1069_n
        1072 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1072 else com.optmizedcode.assets.R.drawable.ic_1072_n
        1087 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1087 else com.optmizedcode.assets.R.drawable.ic_1087_n
        1114 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1114 else com.optmizedcode.assets.R.drawable.ic_1114_n
        1117 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1117 else com.optmizedcode.assets.R.drawable.ic_1117_n
        1135 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1135 else com.optmizedcode.assets.R.drawable.ic_1135_n
        1147 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1147 else com.optmizedcode.assets.R.drawable.ic_1147_n
        1150 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1150 else com.optmizedcode.assets.R.drawable.ic_1150_n
        1153 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1153 else com.optmizedcode.assets.R.drawable.ic_1153_n
        1168 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1168 else com.optmizedcode.assets.R.drawable.ic_1168_n
        1171 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1171 else com.optmizedcode.assets.R.drawable.ic_1171_n
        1188 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1180 else com.optmizedcode.assets.R.drawable.ic_1180_n
        1183 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1183 else com.optmizedcode.assets.R.drawable.ic_1183_n
        1186 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1186 else com.optmizedcode.assets.R.drawable.ic_1186_n
        1189 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1189 else com.optmizedcode.assets.R.drawable.ic_1189_n
        1192 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1192 else com.optmizedcode.assets.R.drawable.ic_1192_n
        1195 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1195 else com.optmizedcode.assets.R.drawable.ic_1195_n
        1198 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1198 else com.optmizedcode.assets.R.drawable.ic_1198_n
        1201 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1201 else com.optmizedcode.assets.R.drawable.ic_1201_n
        1204 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1204 else com.optmizedcode.assets.R.drawable.ic_1204_n
        1207 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1207 else com.optmizedcode.assets.R.drawable.ic_1207_n
        1210 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1210 else com.optmizedcode.assets.R.drawable.ic_1210_n
        1213 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1213 else com.optmizedcode.assets.R.drawable.ic_1213_n
        1216 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1216 else com.optmizedcode.assets.R.drawable.ic_1216_n
        1219 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1219 else com.optmizedcode.assets.R.drawable.ic_1219_n
        1222 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1222 else com.optmizedcode.assets.R.drawable.ic_1222_n
        1225 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1225 else com.optmizedcode.assets.R.drawable.ic_1225_n
        1237 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1237 else com.optmizedcode.assets.R.drawable.ic_1237_n
        1240 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1240 else com.optmizedcode.assets.R.drawable.ic_1240_n
        1243 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1243 else com.optmizedcode.assets.R.drawable.ic_1243_n
        1246 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1246 else com.optmizedcode.assets.R.drawable.ic_1246_n
        1249 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1249 else com.optmizedcode.assets.R.drawable.ic_1249_n
        1252 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1252 else com.optmizedcode.assets.R.drawable.ic_1252_n
        1255 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1255 else com.optmizedcode.assets.R.drawable.ic_1255_n
        1258 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1258 else com.optmizedcode.assets.R.drawable.ic_1258_n
        1261 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1261 else com.optmizedcode.assets.R.drawable.ic_1261_n
        1264 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1264 else com.optmizedcode.assets.R.drawable.ic_1264_n
        1273 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1273 else com.optmizedcode.assets.R.drawable.ic_1273_n
        1276 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1276 else com.optmizedcode.assets.R.drawable.ic_1276_n
        1279 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1279 else com.optmizedcode.assets.R.drawable.ic_1279_n
        1282 -> if (isDay) com.optmizedcode.assets.R.drawable.ic_1282 else com.optmizedcode.assets.R.drawable.ic_1282_n
        else -> {
            if (isDay) com.optmizedcode.assets.R.drawable.ic_1000 else com.optmizedcode.assets.R.drawable.ic_1000_nn
        }
    }
}