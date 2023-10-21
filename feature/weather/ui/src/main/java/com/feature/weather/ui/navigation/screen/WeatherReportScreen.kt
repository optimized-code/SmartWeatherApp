package com.feature.weather.ui.navigation.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Location
import com.feature.weather.ui.R


/*
**************************************************************
 * www.optimizedcode.com
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */
@Preview(
    name = "Dark mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Preview(
    name = "Light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkLightPreviews


//@Preview(name = "5-inch Device Landscape", widthDp = 640, heightDp = 360)
@Preview(name = "5-inch Device Portrait", widthDp = 360, heightDp = 640)
@Composable
fun WeatherReportScreen_onlyForPreview() {
    val landscape = false
    if (landscape) {
        Landscape()
    } else {
        Portrait()
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
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            WeatherSummaryCircle()
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
                //Text(it.current.condition?.text ?: "There is something")
                WeatherSummaryCircle(it.location, it.current)
            }
        }
    }

//    Scaffold(
//        bottomBar = { AppNavigationBar() },
//        containerColor = Color.Transparent,
//        modifier = Modifier
//            .fillMaxSize()
//            .navigationBarsPadding()
//            .statusBarsPadding()
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            contentAlignment = Alignment.Center,
//
//            ) {
//            if (result.isLoading) {
//                CircularProgressIndicator()
//            }
//
//            if (result.error.isNotEmpty()) {
//                Text(result.error)
//            }
//
//            result.success?.let {
//                Text(it.current.condition?.text ?: "There is something")
//            }
//        }
//    }
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

@Composable
fun gradientBg(): Brush {
    return Brush.radialGradient(
        colors = listOf(
            colorResource(id = R.color.wa_primary),
            colorResource(id = R.color.wa_secondary),
        ),
        radius = 1300.0f,
        center = Offset.Zero
    )
}

@Composable
fun summaryCircleGradientBg(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.wa_secondary),
            colorResource(id = R.color.purple_200)
        )
    )
}

@Composable
fun LocationText() {
    Text(
        text = "Whereas",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 20.dp),
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun WeatherSummaryCircle(
    location: Location = Location(), current: Current = Current()
) {
    Box {
        ElevatedCard(
            modifier = Modifier
                .size(width = 260.dp, height = 280.dp)
                .padding(top = 30.dp)
                .align(Alignment.Center)
                .border(BorderStroke(5.dp, gradientBg()), shape = CircleShape),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .background(summaryCircleGradientBg())
                    .fillMaxSize()
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = location.name ?: "Jeddah",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Box(
                    modifier = Modifier.height(85.dp)
                )
                Text(
                    text = current.condition?.text ?: "Clear",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "H: ${current.humidity}, V-km: ${current.visKm}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Row(
            modifier = Modifier
                .height(140.dp)
                .align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${current.tempC ?: ""}°",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 90.sp
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Icon(
                painter = painterResource(id = com.optmizedcode.assets.R.drawable.cloud_3d_200),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(125.dp)
            )
        }
    }
}

@Composable
fun WeatherSummaryCircleConstraintLayout(location: Location = Location()) {
}

@Composable
fun HourlyForecast() {
    /* TODO */
}

@Composable
fun DayWiseForecast() {
    /* TODO */
}