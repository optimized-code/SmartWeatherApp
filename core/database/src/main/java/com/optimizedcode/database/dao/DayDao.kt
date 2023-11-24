package com.optimizedcode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.optimizedcode.database.entities.DayEntity
import com.optimizedcode.database.entities.ForecastEntity

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
interface DayDao {
    @Query("SELECT * FROM day_entity")
    fun getAll(): List<DayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DayEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(day: DayEntity): Long

    @Query("Delete from day_entity")
    fun deleteAll()

}