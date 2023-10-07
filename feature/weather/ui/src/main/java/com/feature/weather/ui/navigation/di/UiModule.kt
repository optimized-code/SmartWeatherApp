package com.feature.weather.ui.navigation.di

import com.feature.weather.ui.navigation.InternalWeatherFeatureApi
import com.feature.weather.ui.navigation.WeatherApi
import com.feature.weather.ui.navigation.WeatherApiImpl
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
 * @package com.feature.weather.ui.navigation.di
 * @date 07-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@Module
@InstallIn(SingletonComponent::class)
object UiModule {
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return WeatherApiImpl()
    }
}