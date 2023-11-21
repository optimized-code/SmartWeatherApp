package com.optimizedcode.location

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.optimizedcode.location
 * @date 21-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */
 
interface LocationUpdateInterface {
    fun onContinuesLocationUpdateRequest(): Flow<LatLng?>
    fun onLastKnownLocationRequest(): Flow<LatLng?>
}