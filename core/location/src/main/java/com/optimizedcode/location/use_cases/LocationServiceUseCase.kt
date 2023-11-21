package com.optimizedcode.location.use_cases

import com.google.android.gms.maps.model.LatLng
import com.optimizedcode.location.LocationUpdateInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import javax.inject.Inject

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.location.use_cases
 * @date 21-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */


class LocationServiceUseCase @Inject constructor(private val locationUpdateInterface: LocationUpdateInterface) {
    operator fun invoke(): Flow<List<String>> =
        locationUpdateInterface.onLastKnownLocationRequest().map {
            if (it != null){
                listOf(it.latitude.toString(), it.longitude.toString())
            } else {
                listOf()
            }
        }
}