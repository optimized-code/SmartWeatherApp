package com.feature.weather.ui.navigation.screen

import android.content.res.Configuration
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Hour
import com.feature.weather.domain.model.Location
import com.feature.weather.ui.R
import com.feature.weather.ui.navigation.faketheme.theme.SmartWeatherForcastAppTheme_Fake
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
@Preview(
    name = "Light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
    widthDp = 360,
    heightDp = 640
)
annotation class DarkLightPreviews


//@Preview(name = "5-inch Device Landscape", widthDp = 640, heightDp = 360)
//@Preview(name = "5-inch Device Portrait", widthDp = 360, heightDp = 640)
@DarkLightPreviews
@Composable
fun WeatherReportScreen_onlyForPreview() {
    val landscape = false
    SmartWeatherForcastAppTheme_Fake {
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(20.dp)
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(25.dp)
//            ) {
            WeatherSummaryCircle()
            AdditionalInfoRow()
            HourlyForecast()
//            }
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
                HourlyForecast(it.forecast[0].hour)
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

@Composable
fun gradientBg(): Brush {

    val colorStops = arrayOf(
        0.2f to MaterialTheme.colorScheme.surface,
        1f to colorResource(id = R.color.wa_secondary)
    )

    return Brush.linearGradient(
        colorStops = colorStops,
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
fun WeatherSummaryCircle(
    location: Location = Location(), current: Current = Current()
) {
    Box {
        ElevatedCard(
            modifier = Modifier
                .size(width = 260.dp, height = 260.dp)
                .padding(top = 10.dp)
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
                    .padding(top = 10.dp, start = 25.dp, end = 25.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = location.name ?: "Jeddah",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 36.sp
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Box(
                    modifier = Modifier.height(95.dp)
                )
                Text(
                    text = "Feels like ${current.feelslikeC}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = current.condition?.text ?: "Clear",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Row(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = "${current.tempC ?: "28"}°",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge.merge(
                    TextStyle(
                        fontSize = 90.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    )
                )
            )

            Icon(
                painter = painterResource(id = com.optmizedcode.assets.R.drawable.cloud_3d_200),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(125.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun HourlyForecast(hours: ArrayList<Hour>? = arrayListOf()) {
    hours?.add(Hour())
    hours?.add(Hour())
    hours?.add(Hour())
    hours?.add(Hour())
    hours?.add(Hour())
    hours?.add(Hour())

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
                color = colorResource(id = R.color.wa_blackWhite),
                modifier = Modifier.padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.next_7_days),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                //color = colorResource(id = R.color.wa_secondary),
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .height(25.dp)
                    .padding(top = 5.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            hours?.let {
                items(items = hours) {
                    HourlyRowItem(it)
                }
            }
        }
    }
}

@Composable
fun HourlyRowItem_old(hour: Hour = Hour()) {
    Column(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .size(60.dp)
                .border(
                    BorderStroke(1.dp, colorResource(id = R.color.wa_secondary)),
                    shape = RoundedCornerShape(10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${hour.tempC ?: "27"}°",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 20.sp
                ),
                color = colorResource(id = R.color.wa_secondary)
            )
            hour.time?.split(" ")?.get(1)?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    color = colorResource(id = R.color.wa_secondary)
                )
            }
        }
        Icon(
            painter = painterResource(id = com.optmizedcode.assets.R.drawable.cloud_3d_200),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun AdditionalInfoRow(humidity: Int? = 80, windSpeed: Double? = 20.0, visibility: Int? = 10) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AdditionalInfoItem("${humidity}%", com.optmizedcode.assets.R.drawable.drizzle)
        AdditionalInfoItem("${windSpeed?.toInt()}km/h", com.optmizedcode.assets.R.drawable.breezy)
        AdditionalInfoItem("${visibility}km", com.optmizedcode.assets.R.drawable.visibility)
    }
}

@Composable
fun AdditionalInfoItem(value: String, icon: Int) {
    Column(
        modifier = Modifier.width(55.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .width(55.dp)
                .height(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface //colorResource(id = R.color.white_90)
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.wa_blackWhite),
        )
    }
}

@Composable
fun HourlyRowItem(hour: Hour = Hour(time = "123 00:00")) {
    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.wa_whiteBlack)
        ),
        shape = RoundedCornerShape(40.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.width(55.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "00:00",
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(id = R.color.wa_secondary),
                modifier = Modifier.padding(top = 8.dp)
            )
            Icon(
                painter = painterResource(id = com.optmizedcode.assets.R.drawable.cloud_3d_200),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "${hour.tempC ?: "27"}°",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 20.sp,
                    letterSpacing = 0.sp
                ),
                color = colorResource(id = R.color.wa_secondary),
                modifier = Modifier.padding(bottom = 5.dp),
                textAlign = TextAlign.Center
            )


//            hour.time?.split(" ")?.get(1)?.let {
//                Text(
//                    text = it,
//                    style = MaterialTheme.typography.headlineSmall,
//                    color = colorResource(id = R.color.wa_secondary)
//                )
//            }
        }
    }
}

@Composable
fun DayWiseForecast() {
    /* TODO */
}