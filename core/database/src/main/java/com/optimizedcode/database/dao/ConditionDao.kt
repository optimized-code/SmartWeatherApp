package com.optimizedcode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.optimizedcode.database.entities.ConditionEntity
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
interface ConditionDao {
    @Query("SELECT * FROM condition_entity")
    fun getAll(): List<ConditionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ConditionEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(condition: ConditionEntity): Long

    @Query("Delete from condition_entity")
    fun deleteAll()

}