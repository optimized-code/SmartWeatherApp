package com.optimizedcode.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.optimizedcode.database.dao.AstroDao
import com.optimizedcode.database.dao.ConditionDao
import com.optimizedcode.database.dao.DayDao
import com.optimizedcode.database.dao.ForecastDao
import com.optimizedcode.database.dao.HourDao
import com.optimizedcode.database.entities.AstroEntity
import com.optimizedcode.database.entities.ConditionEntity
import com.optimizedcode.database.entities.DayEntity
import com.optimizedcode.database.entities.ForecastEntity
import com.optimizedcode.database.entities.HourEntity

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.database
 * @date 23-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Database(
    entities = [
        ForecastEntity::class,
        DayEntity::class,
        AstroEntity::class,
        HourEntity::class,
        ConditionEntity::class
    ], version = 1, exportSchema = false
)
abstract class RoomDatabaseClient : RoomDatabase() {
    abstract fun ForecastDao(): ForecastDao
    abstract fun DayDao(): DayDao
    abstract fun AstroDao(): AstroDao
    abstract fun HourDao(): HourDao
    abstract fun ConditionDao(): ConditionDao

    companion object {
        @Volatile
        private var instance: RoomDatabaseClient? = null
        fun getInstance(context: Context): RoomDatabaseClient {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DB_NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .build()
        }
    }
}