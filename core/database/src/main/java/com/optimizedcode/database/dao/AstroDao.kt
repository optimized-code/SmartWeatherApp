package com.optimizedcode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.optimizedcode.database.entities.AstroEntity
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
interface AstroDao {
    @Query("SELECT * FROM astro_entity")
    fun getAll(): List<AstroEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<AstroEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(astro: AstroEntity): Long

    @Query("Delete from astro_entity")
    fun deleteAll()

}