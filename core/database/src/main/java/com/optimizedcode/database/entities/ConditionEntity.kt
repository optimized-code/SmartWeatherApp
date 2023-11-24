package com.optimizedcode.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.optimizedcode.database.condition_entity

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

@Entity(tableName = condition_entity)
data class ConditionEntity(
    @PrimaryKey(autoGenerate = true) val condition_id: Long = 0,
    @ColumnInfo(name = "hour_id") val hour_id: Long? = null,
    @ColumnInfo(name = "day_id") val day_id: Long? = null,
    @ColumnInfo(name = "text") val text: String? = null,
    @ColumnInfo(name = "icon") val icon: String? = null,
    @ColumnInfo(name = "code") val code: Int? = null
)