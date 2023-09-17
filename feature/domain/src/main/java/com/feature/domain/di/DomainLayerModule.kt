package com.feature.domain.di

import com.feature.domain.repo.WeatherReportDataRepo
import com.feature.domain.use_cases.GetWeatherReportDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.domain.di
 * @date 16-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {
    @Provides
    fun provideGetWeatherReportDataUseCase(weatherReportDataRepo: WeatherReportDataRepo): GetWeatherReportDataUseCase {
        return GetWeatherReportDataUseCase(weatherReportDataRepo)
    }
}