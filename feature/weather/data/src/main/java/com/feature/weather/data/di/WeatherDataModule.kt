package com.feature.weather.data.di

import com.feature.weather.data.repo.WeatherReportDataRepoImpl
import com.feature.weather.domain.repo.WeatherReportDataRepo
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
    fun provideWeatherReportDataRepo(weatherDataProviders: WeatherDataProviders): WeatherReportDataRepo {
        return WeatherReportDataRepoImpl(weatherDataProviders)
    }
}