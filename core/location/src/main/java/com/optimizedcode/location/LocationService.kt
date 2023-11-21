package com.optimizedcode.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
}

fun getLocationClient(): LocationRequest {
    val request = LocationRequest.Builder(10000L)
    request.setPriority(Priority.PRIORITY_HIGH_ACCURACY)
    return request.build()
}

@SuppressLint("MissingPermission")
private fun getLastKnownLocation(context: Context): Location? {

    return null

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers = locationManager.getProviders(true)
    var result: Location? = null
    for (provider in providers) {
        val location = locationManager.getLastKnownLocation(provider) ?: continue
        if (result == null || location.accuracy < result.accuracy) {
            result = location
        }
    }

    return result
}

class LocationService(
    private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationUpdateInterface {
    @SuppressLint("MissingPermission")
    override fun onContinuesLocationUpdateRequest(): Flow<LatLng?> = callbackFlow {
        if (context.hasLocationPermission()) {
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    locationResult.locations.lastOrNull()?.let {
                        trySend(LatLng(it.latitude, it.longitude))
                    }
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                getLocationClient(),
                locationCallback,
                Looper.getMainLooper()
            )
            awaitClose { fusedLocationProviderClient.removeLocationUpdates(locationCallback) }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onLastKnownLocationRequest(): Flow<LatLng?> = callbackFlow {
        if (context.hasLocationPermission()) {
            val location = getLastKnownLocation(context)
            if (location == null) {
                onContinuesLocationUpdateRequest().collect {
                    it?.let {
                        trySend(LatLng(it.latitude, it.longitude))
                    }
                }
            } else {
                trySend(LatLng(location.latitude, location.longitude))
            }

            awaitClose()
        }
    }
}