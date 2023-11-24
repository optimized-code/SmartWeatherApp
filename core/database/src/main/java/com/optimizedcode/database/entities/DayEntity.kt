package com.optimizedcode.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optimizedcode.database.day_entity

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.database.entities
 * @date 24-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Entity(tableName = day_entity)
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val day_id: Long = 0,
//    @ColumnInfo(name = "forecast_id") val forecast_id: Long? = null,
    @ColumnInfo(name = "maxtemp_c") val maxtemp_c: Double? = null,
    @ColumnInfo(name = "maxtemp_f") val maxtemp_f: Double? = null,
    @ColumnInfo(name = "mintemp_c") val mintemp_c: Double? = null,
    @ColumnInfo(name = "mintemp_f") val mintemp_f: Double? = null,
    @ColumnInfo(name = "avgtemp_c") val avgtemp_c: Double? = null,
    @ColumnInfo(name = "avgtemp_f") val avgtemp_f: Double? = null,
    @ColumnInfo(name = "maxwind_mph") val maxwind_mph: Double? = null,
    @ColumnInfo(name = "maxwind_kph") val maxwind_kph: Double? = null,
    @ColumnInfo(name = "totalprecip_mm") val totalprecip_mm: Double? = null,
    @ColumnInfo(name = "totalprecip_in") val totalprecip_in: Double? = null,
    @ColumnInfo(name = "totalsnow_cm") val totalsnow_cm: Double? = null,
    @ColumnInfo(name = "avgvis_km") val avgvis_km: Double? = null,
    @ColumnInfo(name = "avgvis_miles") val avgvis_miles: Double? = null,
    @ColumnInfo(name = "avghumidity") val avghumidity: Double? = null,
    @ColumnInfo(name = "daily_will_it_rain") val daily_will_it_rain: Double? = null,
    @ColumnInfo(name = "daily_chance_of_rain") val daily_chance_of_rain: Double? = null,
    @ColumnInfo(name = "daily_will_it_snow") val daily_will_it_snow: Double? = null,
    @ColumnInfo(name = "daily_chance_of_snow") val daily_chance_of_snow: Double? = null,
    @Embedded val condition: Condition? = null,
    @ColumnInfo(name = "uv") val uv: Double? = null
)