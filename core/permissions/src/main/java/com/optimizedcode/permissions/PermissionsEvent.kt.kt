package com.optimizedcode.permissions

import androidx.activity.result.contract.ActivityResultContracts

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author ehtisham
 * @package com.optimizedcode.permissions
 * @date 10-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

sealed class PermissionsEvent<T>(
    val message: String = "",
    val isGranted: Boolean = false,
    val permanentlyDenied: Boolean = false
) {
    class onGrantedPermission<T> : PermissionsEvent<T>()
    class onPermanentlyDenied<T>(permanentlyDenied: Boolean) :
        PermissionsEvent<T>(permanentlyDenied = permanentlyDenied)
    class onGranted<T>(isGranted: Boolean) : PermissionsEvent<T>(isGranted = isGranted)
}