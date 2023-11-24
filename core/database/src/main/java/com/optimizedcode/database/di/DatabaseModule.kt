package com.optimizedcode.database.di

import android.content.Context
import com.optimizedcode.database.RoomDatabaseClient
import com.optimizedcode.database.dao.AstroDao
import com.optimizedcode.database.dao.ConditionDao
import com.optimizedcode.database.dao.DayDao
import com.optimizedcode.database.dao.ForecastDao
import com.optimizedcode.database.dao.HourDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.database.di
 * @date 24-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomDatabaseClient {
        return RoomDatabaseClient.getInstance(context)
    }

    @Provides
    fun provideForecastDao(roomDatabaseClient: RoomDatabaseClient): ForecastDao {
        return roomDatabaseClient.ForecastDao()
    }

    @Provides
    fun provideDayDao(roomDatabaseClient: RoomDatabaseClient): DayDao {
        return roomDatabaseClient.DayDao()
    }

    @Provides
    fun provideHourDao(roomDatabaseClient: RoomDatabaseClient): HourDao {
        return roomDatabaseClient.HourDao()
    }

    @Provides
    fun provideAstroDao(roomDatabaseClient: RoomDatabaseClient): AstroDao {
        return roomDatabaseClient.AstroDao()
    }

    @Provides
    fun provideConditionDao(roomDatabaseClient: RoomDatabaseClient): ConditionDao {
        return roomDatabaseClient.ConditionDao()
    }
}