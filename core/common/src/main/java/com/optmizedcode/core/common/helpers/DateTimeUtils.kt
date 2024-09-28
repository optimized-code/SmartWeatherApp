package com.optmizedcode.core.common.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


object DateTimeUtils {

    private const val ONE_DAY_IN_MILLIS = 86400000L

    fun isDay(currentHour: Int = Calendar.getInstance()[Calendar.HOUR_OF_DAY]): Boolean {
        return when (currentHour) {
            in 0..4 -> false
            in 5..17 -> true
            in 18..23 -> false
            else -> true
        }
    }

    private fun Int.toStrDay(): String{
        return when(this){
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else ->
                "Noday"
        }
    }

    /*
    [param] dateInStr:
     */
    fun getDateLong(dateInStr: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date: Date? = sdf.parse(dateInStr)
        return if (date?.time == null) 0 else date.time
    }

    /*
    Gets current date and time in milli seconds e.g. 86400000L
     */
    fun getCurrentDate(): Long{
        return System.currentTimeMillis()
    }

    /*
    [param] date: 1701187771888
    [param] days: Number of days in integer to add in given date e.g. 1,2,3 etc.
     */
    fun addDays(date: Long, days: Int): Long{
        return date + (ONE_DAY_IN_MILLIS * days)
    }

    /*
    [param] date: 1701187771888
    [param] days: Number of days in integer to subtract from given date e.g. 1,2,3 etc.
     */
    fun minusDays(date: Long, days: Int): Long{
        return date - (ONE_DAY_IN_MILLIS * days)
    }

    /*
    [param] millis: 1701187771888
    Calculate and return days from millis e.g. 86400000L = 1 day
     */
    fun getDaysFromMillis(millis: Long): Int{
        return TimeUnit.MILLISECONDS.toDays(millis).toInt()
    }

    /*
    [param] millis: 1701187771888
    Calculate number of days into a string in a format e.g. totalDays = 600
    Returns: 1 y 235 days
     */
    fun formatDays(totalDays: Int): String {
        val years = (totalDays / 365)
        val days = totalDays - (365 * years)

        return if (years > 0){
            "$years y $days days"
        } else {
            "$days"
        }
    }

    /*
    [param] millis: 1701129600
    Format date in "29Sep, Monday" format
     */
    fun convertEpochDateIntoFormattedString(millis: Long?): String {
        if (millis == null){
            return "No date available"
        }

        val dateFormat = "dd MMM, E"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calender = Calendar.getInstance()
        calender.timeInMillis = (millis*1000)
        return simpleDateFormat.format(calender.time)
    }

    // e.g 2023-11-03
//    fun getDayFromDate(date: String): String {
//        val dateFormat = "dd MMM, E"
//        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
//        val calender = Calendar.getInstance()
//        calender.timeInMillis = da
//
//        calender.set(Calendar.YEAR, 2023)
//        calender.set(Calendar.MONTH, 1)
//        calender.set(Calendar.DAY_OF_MONTH, 2)
//        return calender.get(Calendar.DAY_OF_WEEK).toStrDay()
//    }


}