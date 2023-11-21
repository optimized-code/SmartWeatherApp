package com.optimizedcode.location.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.optimizedcode.location.LocationService
import com.optimizedcode.location.LocationUpdateInterface
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
 * @package com.optimizedcode.location.di
 * @date 21-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {
    @Singleton
    @Provides
    fun provideLocationService(
        @ApplicationContext context: Context
    ): LocationUpdateInterface = LocationService(
        context, LocationServices.getFusedLocationProviderClient(context)
    )
}
