package com.optmizedcode.smartweatherforcast.di

import com.feature.weather.ui.navigation.WeatherApi
import com.optmizedcode.smartweatherforcast.navigation.NavigationProvider
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
 * @package com.optmizedcode.smartweatherforcast.di
 * @date 07-Oct-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideNavigationProvider (weatherApi: WeatherApi): NavigationProvider{
        return NavigationProvider(weatherApi)
    }
}