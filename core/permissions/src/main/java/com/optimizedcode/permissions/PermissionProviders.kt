package com.optimizedcode.permissions

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

interface LocationPermissionProvider {
    fun getPermissionDescription(isPermanentlyDenied: Boolean): String
}

class LocationPermissionTextProvider :
    LocationPermissionProvider {

    override fun getPermissionDescription(isPermanentlyDenied: Boolean): String {
        return if (isPermanentlyDenied) {
            "This permission is required to get current location weather. In order to get current " +
                    "location weather please grant permission from settings"
        } else {
            "Please grant location permission to get precise weather conditions for your " +
                    "current area"
        }
    }
}


