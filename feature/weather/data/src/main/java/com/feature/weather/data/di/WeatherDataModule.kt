package com.feature.weather.data.di

import com.feature.weather.data.repo.ForecastRepoImpl
import com.feature.weather.data.repo.WeatherReportDataRepoImpl
import com.feature.weather.domain.repo.ForecastRepo
import com.feature.weather.domain.repo.WeatherReportDataRepo
import com.optimizedcode.database.dao.AstroDao
import com.optimizedcode.database.dao.ConditionDao
import com.optimizedcode.database.dao.DayDao
import com.optimizedcode.database.dao.ForecastDao
import com.optimizedcode.database.dao.HourDao
import com.optmizedcode.core.network.dataproviders.WeatherDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.data.di
 * @date 15-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object WeatherDataModule {
    @Provides
    fun provideWeatherReportDataRepo(
        weatherDataProviders: WeatherDataProviders,
        forecastDao: ForecastDao,
        hourDao: HourDao
    ): WeatherReportDataRepo {
        return WeatherReportDataRepoImpl(
            weatherDataProviders,
            forecastDao,
            hourDao
        )
    }

    @Provides
    fun provideForecastDataRepo(
        forecastDao: ForecastDao
    ): ForecastRepo {
        return ForecastRepoImpl(forecastDao)
    }
}