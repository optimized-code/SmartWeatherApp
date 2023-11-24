package com.optimizedcode.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optimizedcode.database.condition_entity
import com.optimizedcode.database.hour_entity

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

@Entity(tableName = hour_entity)
data class HourEntity(
    @PrimaryKey(autoGenerate = true) val hour_id: Long = 0,
    @ColumnInfo(name = "forecast_id") val forecast_id: Long? = null,
    @ColumnInfo(name = "chanceOfRain") val chanceOfRain: Long? = null,
    @ColumnInfo(name = "chanceOfSnow") val chanceOfSnow: Long? = null,
    @ColumnInfo(name = "cloud") val cloud: Long? = null,
    @ColumnInfo(name = "dewpointC") val dewpointC: Double? = null,
    @ColumnInfo(name = "dewpointF") val dewpointF: Double? = null,
    @ColumnInfo(name = "feelslikeC") val feelslikeC: Double? = null,
    @ColumnInfo(name = "feelslikeF") val feelslikeF: Double? = null,
    @ColumnInfo(name = "gustKph") val gustKph: Double? = null,
    @ColumnInfo(name = "gustMph") val gustMph: Double? = null,
    @ColumnInfo(name = "heatindexC") val heatindexC: Double? = null,
    @ColumnInfo(name = "heatindexF") val heatindexF: Double? = null,
    @ColumnInfo(name = "humidity") val humidity: Long? = null,
    @ColumnInfo(name = "isDay") val isDay: Long? = null,
    @ColumnInfo(name = "precipIn") val precipIn: Double? = null,
    @ColumnInfo(name = "precipMm") val precipMm: Double? = null,
    @ColumnInfo(name = "pressureIn") val pressureIn: Double? = null,
    @ColumnInfo(name = "pressureMb") val pressureMb: Double? = null,
    @ColumnInfo(name = "tempC") val tempC: Double? = null,
    @ColumnInfo(name = "tempF") val tempF: Double? = null,
    @ColumnInfo(name = "time") val time: String? = null,
    @ColumnInfo(name = "timeEpoch") val timeEpoch: Long? = null,
    @ColumnInfo(name = "uv") val uv: Double? = null,
    @ColumnInfo(name = "visKm") val visKm: Double? = null,
    @ColumnInfo(name = "visMiles") val visMiles: Double? = null,
    @ColumnInfo(name = "willItRain") val willItRain: Long? = null,
    @ColumnInfo(name = "willItSnow") val willItSnow: Long? = null,
    @ColumnInfo(name = "windDegree") val windDegree: Long? = null,
    @ColumnInfo(name = "windDir") val windDir: String? = null,
    @ColumnInfo(name = "windKph") val windKph: Double? = null,
    @ColumnInfo(name = "windMph") val windMph: Double? = null,
    @ColumnInfo(name = "windchillC") val windchillC: Double? = null,
    @ColumnInfo(name = "windchillF") val windchillF: Double? = null,
    @Embedded val condition: Condition? = null
)

data class Condition(
    @ColumnInfo(name = "text") val text: String? = null,
    @ColumnInfo(name = "icon") val icon: String? = null,
    @ColumnInfo(name = "code") val code: Int? = null
)