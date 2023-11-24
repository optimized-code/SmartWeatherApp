package com.optimizedcode.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optimizedcode.database.forecast_entity

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.database.entities
 * @date 23-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Entity(tableName = forecast_entity)
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "date_epoch") val dateEpoch: Long? = null,
    @Embedded val day: DayEntity? = null,
    @Embedded val astro: AstroEntity? = null
)

