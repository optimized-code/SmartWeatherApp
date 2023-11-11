package com.feature.weather.ui.navigation.screen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.feature.weather.ui.R
import com.feature.weather.ui.navigation.composables.AdditionalInfoRow
import com.feature.weather.ui.navigation.composables.HourlyForecast
import com.feature.weather.ui.navigation.composables.LinearLabelsSingleAttributeItem
import com.feature.weather.ui.navigation.composables.WeatherSummaryCircle
import com.feature.weather.ui.navigation.composables.WindInfoItem
import com.feature.weather.ui.navigation.composables.gradientBg
import com.feature.weather.ui.navigation.faketheme.theme.SmartWeatherForecastAppTheme_Fake
import com.feature.weather.ui.navigation.helpers.getFeelsLikeMessage
import com.feature.weather.ui.navigation.helpers.getPrecipitationMessage
import com.feature.weather.ui.navigation.helpers.getUVIndexIntensity
import com.feature.weather.ui.navigation.helpers.getUVIndexMessage

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Portrait5Inch
@Composable
fun WeatherReportScreen_onlyForPreview() {
    val landscape = false
    SmartWeatherForecastAppTheme_Fake {
        if (landscape) {
            Landscape()
        } else {
            Portrait()
        }
    }
}

@Composable
fun Portrait() {
    Scaffold(
        bottomBar = { AppNavigationBar() },
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(gradientBg())
    ) { innerPadding ->
        // Main column with all placeholders
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            WeatherSummaryCircle()
            AdditionalInfoRow()
            HourlyForecast()

            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                LinearLabelsSingleAttributeItem(
                    icon = com.optmizedcode.assets.R.drawable.ic_wb_sunny,
                    heading = stringResource(R.string.uv_index),
                    value = "8",
                    valueDescription = getUVIndexIntensity(8).getString(),
                    shortDescription = getUVIndexMessage(8).getString()
                )
                LinearLabelsSingleAttributeItem(
                    icon = com.optmizedcode.assets.R.drawable.ic_vector_thermostat,
                    heading = stringResource(R.string.feels_like),
                    value = "27°",
                    valueDescription = "",
                    shortDescription = getFeelsLikeMessage(27, 27).getString()
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                LinearLabelsSingleAttributeItem(
                    icon = com.optmizedcode.assets.R.drawable.ic_water_drop,
                    heading = stringResource(R.string.precipitation),
                    value = "0 mm",
                    valueDescription = "in last 24hr",
                    shortDescription = getPrecipitationMessage(arrayListOf()).getString()
                )

                LinearLabelsSingleAttributeItem(
                    icon = com.optmizedcode.assets.R.drawable.ic_vector_sunrise_1,
                    heading = stringResource(R.string.sun_rise),
                    value = "06:53",
                    valueDescription = stringResource(R.string.sunset),
                    shortDescription = "07:01"
                )
            }

            WindInfoItem(8, 20)
        }
    }
}

@Composable
fun Landscape() {
    Scaffold(
        bottomBar = { AppNavigationRail() },
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(gradientBg())
    ) { innerPadding ->
        // Main column with all placeholders
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            WeatherSummaryCircle()
            AdditionalInfoRow()
            HourlyForecast()
        }
    }
}

@Composable
fun InitViewWeather() {

    val isGranted = ContextCompat.checkSelfPermission(
        LocalContext.current,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED


    val isGrantedState = remember {
        mutableStateOf(
            isGranted
        )
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            isGrantedState.value = it
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = gradientBg()
            )
    )
    {
        if (isGrantedState.value){
            val viewModel = hiltViewModel<TodayWeatherReportViewModel>()
            WeatherReportScreen(viewModel = viewModel)
        } else {
            LaunchedEffect(key1 = locationPermissionLauncher){
                locationPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }
}


@Composable
fun WeatherReportScreen(viewModel: TodayWeatherReportViewModel) {
    val result = viewModel.weatherReportData.value
    Scaffold(
        bottomBar = { AppNavigationBar() },
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(gradientBg())
    ) { innerPadding ->
        // Main column with all placeholders
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            if (result.isLoading) {
                CircularProgressIndicator()
            }

            if (result.error.isNotEmpty()) {
                Text(result.error)
            }

            result.success?.let {
                WeatherSummaryCircle(it.location, it.current)
                AdditionalInfoRow(it.current.humidity, it.current.windKph, it.current.visKm)
                viewModel.checkHourChange()
                HourlyForecast(it.forecast[0].hour, viewModel.currentHour)

                val uvIndex = it.current.uv ?: 0
                val feelsLike = it.current.feelslikeC?.toInt() ?: 0
                val actualTemp = it.current.tempC ?: 0
                val precipitation = it.current.precipIn ?: 0

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    LinearLabelsSingleAttributeItem(
                        icon = com.optmizedcode.assets.R.drawable.ic_wb_sunny,
                        heading = stringResource(R.string.uv_index),
                        value = uvIndex.toString(),
                        valueDescription = getUVIndexIntensity(uvIndex).getString(),
                        shortDescription = getUVIndexMessage(uvIndex).getString()
                    )
                    LinearLabelsSingleAttributeItem(
                        icon = com.optmizedcode.assets.R.drawable.ic_water_drop,
                        heading = stringResource(R.string.feels_like),
                        value = "${feelsLike}°",
                        valueDescription = "",
                        shortDescription = getFeelsLikeMessage(
                            actualTemp,
                            feelsLike
                        ).getString()
                    )
                }

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    LinearLabelsSingleAttributeItem(
                        icon = com.optmizedcode.assets.R.drawable.ic_water_drop,
                        heading = stringResource(R.string.pressure),
                        value = "$precipitation mm",
                        valueDescription = stringResource(R.string.in_last_24hr),
                        shortDescription = getPrecipitationMessage(it.forecast).getString()
                    )
                    LinearLabelsSingleAttributeItem(
                        icon = com.optmizedcode.assets.R.drawable.ic_vector_sunrise_1,
                        heading = stringResource(R.string.sun_rise),
                        value = it.forecast[0].astro?.sunrise?.replace(" ", "") ?: "",
                        valueDescription = stringResource(R.string.sunset),
                        shortDescription = it.forecast[0].astro?.sunset ?: ""
                    )
                }

                WindInfoItem(it.current.windKph?.toInt() ?: 0, it.current.gustKph?.toInt() ?: 0)
            }
        }
    }
}

@Composable
fun AppNavigationRail() {
    NavigationRail(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
            )

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                }
            )

            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Face, contentDescription = null)
                }
            )
        }
    }
}

@Composable
fun AppNavigationBar() {
    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            })

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            })

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            label = {
                Text(
                    text = stringResource(R.string.saved_cities)
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            label = {
                Text(text = stringResource(R.string.settings))
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            },
            colors = androidx.compose.material3.NavigationBarItemDefaults
                .colors(
                    selectedIconColor = Color.Unspecified,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        LocalAbsoluteTonalElevation.current
                    )
                )
        )

    }
}




