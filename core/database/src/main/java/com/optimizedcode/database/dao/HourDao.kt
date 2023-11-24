package com.optimizedcode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.optimizedcode.database.entities.ForecastEntity
import com.optimizedcode.database.entities.HourEntity

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.database.dao
 * @date 24-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Dao
interface HourDao {
    @Query("SELECT * FROM hour_entity")
    fun getAll(): List<HourEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<HourEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hour: HourEntity): Long

    @Query("Delete from hour_entity")
    fun deleteAll()
}