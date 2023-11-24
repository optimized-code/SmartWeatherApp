package com.optimizedcode.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optimizedcode.database.astro_entity

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

@Entity(tableName = astro_entity)
data class AstroEntity(
    @PrimaryKey(autoGenerate = true) val astro_id: Long = 0,
//    @ColumnInfo(name = "forecast_id") val forecast_id: Long? = null,
    @ColumnInfo(name = "sunrise") val sunrise: String? = null,
    @ColumnInfo(name = "sunset") val sunset: String? = null,
    @ColumnInfo(name = "moonrise") val moonrise: String? = null,
    @ColumnInfo(name = "moonset") val moonset: String? = null,
    @ColumnInfo(name = "moon_phase") val moon_phase: String? = null,
    @ColumnInfo(name = "moon_illumination") val moon_illumination: String? = null,
    @ColumnInfo(name = "is_moon_up") val is_moon_up: Boolean? = null,
    @ColumnInfo(name = "is_sun_up") val is_sun_up: Boolean? = null
)