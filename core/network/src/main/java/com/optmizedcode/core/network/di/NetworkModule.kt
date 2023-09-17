package com.optmizedcode.core.network.di

import com.optmizedcode.core.network.ApiService
import com.optmizedcode.core.network.BuildConfig
import com.optmizedcode.core.network.dataproviders.WeatherDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.di
 * @date 10-Sep-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APP_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideWeatherReportDataProvider(apiService: ApiService): WeatherDataProviders{
        return WeatherDataProviders(apiService)
    }
}