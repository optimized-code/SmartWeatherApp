package com.optimizedcode.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.optimizedcode.database.entities.AstroEntity
import com.optimizedcode.database.entities.DayEntity
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
interface ForecastDao {
    @Query("SELECT * FROM forecast_entity")
    fun getAll(): List<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ForecastEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: ForecastEntity): Long

    @Query("DELETE FROM forecast_entity")
    fun deleteAll()

    data class CompleteForecast(
        @Embedded val forecast: ForecastEntity,
        @Relation(
            entity = HourEntity::class,
            parentColumn = "id",
            entityColumn = "forecast_id"
        )
        val hours: List<HourEntity>
    )

    @Transaction
    @Query("SELECT * FROM forecast_entity")
    fun getCompleteForecast(): List<CompleteForecast>
}