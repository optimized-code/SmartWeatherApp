package com.feature.weather.domain.di

import com.feature.weather.domain.repo.WeatherReportDataRepo
import com.feature.weather.domain.use_cases.GetWeatherReportDataUseCase
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
 * @package com.feature.domain.di
 * @date 16-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {
    @Provides
    fun provideGetWeatherReportDataUseCase(weatherReportDataRepo: WeatherReportDataRepo): GetWeatherReportDataUseCase {
        return GetWeatherReportDataUseCase(weatherReportDataRepo)
    }
}