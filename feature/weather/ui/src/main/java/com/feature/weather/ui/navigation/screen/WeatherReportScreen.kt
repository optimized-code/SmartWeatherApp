package com.feature.weather.ui.navigation.screen

import android.content.res.Configuration
import android.util.Config
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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Forecastday
import com.feature.weather.domain.model.Hour
import com.feature.weather.domain.model.Location
import com.feature.weather.ui.R
import com.feature.weather.ui.navigation.faketheme.theme.SmartWeatherForcastAppTheme_Fake
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.math.roundToInt

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


//@DarkLightPreviews
//@Preview(name = "5-inch Device Landscape", widthDp = 640, heightDp = 360)
@Preview(name = "5-inch Device Portrait", widthDp = 360, heightDp = 640)
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
                        shortDescription = getFeelsLikeMessage(actualTemp, feelsLike).getString()
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
                        value = it.forecast[0].astro?.sunrise ?: "",
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
fun WeatherSummaryCircle(location: Location = Location(), current: Current = Current()) {
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
                    text = "Feels like ${current.feelslikeC ?: "29"}°",
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
                .height(160.dp)
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
fun HourlyForecast(hours: ArrayList<Hour>? = arrayListOf(), currentHour: Int = 0) {
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

        val listState = rememberLazyListState()
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            state = listState
        ) {
            hours?.let {
                items(items = hours) {
                    HourlyRowItem(it, currentHour)
                }
            }
        }

        LaunchedEffect(key1 = currentHour) {
            delay(1500)
            listState.animateScrollToItem(currentHour)
        }
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
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun HourlyRowItem(hour: Hour, currentHour: Int) {
    val time = hour.time?.split(" ")?.get(1)
    val incomingHour = time?.split(":")?.get(0)?.toInt()
    val textColor = if (incomingHour == currentHour) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.wa_secondary)
    }

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (incomingHour == currentHour) {
                colorResource(id = R.color.wa_secondary)
            } else {
                colorResource(id = R.color.wa_whiteBlack)
            }

        ),
        shape = RoundedCornerShape(40.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.width(55.dp).height(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            hour.time?.split(" ")?.get(1)?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    color = textColor,
                    modifier = Modifier.padding(top = 7.dp),
                    textAlign = TextAlign.Center
                )
            }
            Icon(
                painter = painterResource(id = getWeatherIcon(hour.condition?.code)),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "${hour.tempC?.roundToInt() ?: "27"}°",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 20.sp,
                    letterSpacing = 0.sp
                ),
                color = textColor,
                modifier = Modifier.padding(bottom = 5.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LinearLabelsSingleAttributeItem(
    icon: Int = com.optmizedcode.assets.R.drawable.ic_1000,
    heading: String = "Heading",
    value: String = "0",
    valueDescription: String = "Low",
    shortDescription: String = "Use sun protection 9AM - 4PM."
) {
    ElevatedCard(
        modifier = Modifier.size(150.dp).background(Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        // heading
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null, tint = Color.DarkGray,
                modifier = Modifier
                    .size(25.dp)
                    .padding(5.dp)
            )
            Text(
                text = heading,
                style = MaterialTheme.typography.titleSmall,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp).background(Color.Transparent)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp
                ),
                color = colorResource(id = R.color.purple_500),
                textAlign = TextAlign.Center
            )
            Text(
                text = valueDescription,
                style = MaterialTheme.typography.titleLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
            Text(
                text = shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun WindInfoItem(windKm: Int, gustKm: Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .height(140.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        // heading
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = com.optmizedcode.assets.R.drawable.ic_vector_wind),
                contentDescription = null, tint = Color.DarkGray,
                modifier = Modifier
                    .size(25.dp)
                    .padding(5.dp)
            )
            Text(
                text = stringResource(id = R.string.wind),
                style = MaterialTheme.typography.titleSmall,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "$windKm",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 40.sp,
                        lineHeight = 0.5.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    color = colorResource(id = R.color.purple_500)
                )
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
                    Text(
                        text = stringResource(R.string.km_h),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.wind).toLowerCase(Locale.ENGLISH),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "$gustKm",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 40.sp,
                        lineHeight = 0.5.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    color = colorResource(id = R.color.purple_500)
                )
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)){
                    Text(
                        text = stringResource(R.string.km_h),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.gust),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

// BACKGROUNDS ////////////////////////////////////////////////////////////////
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

// HELPER FUNCTIONS ///////////////////////////////////////////////////////////
fun getPrecipitationMessage(days: ArrayList<Forecastday>): UiText{
    for (day in days){
        day.day?.let {
            if ((it.totalprecip_mm ?: 0.0) > 0.0){
                return UiText.StringResourceValue(R.string.expected_mm_rain_on, it.totalprecip_mm ?: 0.0, day.date ?: "")
            }
        }
    }

    return UiText.StringResourceValue(R.string.no_rain_expected)
}
fun getUVIndexMessage(uvIndex: Int): UiText{
    return if (uvIndex <= 2){
        UiText.StringResourceValue(R.string.no_need_to_use_sun_protection)
    }
    else if (uvIndex in 3..5) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_2pm)
    }
    else if (uvIndex in 6..7) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_3pm)
    }
    else if (uvIndex in 8..10) {
        UiText.StringResourceValue(R.string.use_sun_protection_between_9am_4pm)
    }
    else if (uvIndex in 11..100) {
        UiText.StringResourceValue(R.string.avoid_direct_sunlight_interaction)
    }
    else {
        UiText.StringResourceValue(R.string.use_sun_protection_to_be_safe)
    }
}
fun getFeelsLikeMessage(actualTemp: Int, feelsLikeTemp: Int): UiText{
    return if (actualTemp == feelsLikeTemp){
        UiText.StringResourceValue(R.string.similar_to_the_actual_temperature)
    }
    else if (actualTemp > feelsLikeTemp){
        UiText.StringResourceValue(R.string.wind_is_making_it_cooler)
    }
    else {
        UiText.StringResourceValue(R.string.humidity_is_making_it_warmer)
    }
}
fun getUVIndexIntensity(uvIndex: Int): UiText{
    return if (uvIndex <= 2){
        UiText.StringResourceValue(R.string.low)
    }
    else if (uvIndex in 3..5) {
        UiText.StringResourceValue(R.string.moderate)
    }
    else if (uvIndex in 6..7) {
        UiText.StringResourceValue(R.string.high)
    }
    else if (uvIndex in 8..10) {
        UiText.StringResourceValue(R.string.very_high)
    }
    else if (uvIndex in 11..100) {
        UiText.StringResourceValue(R.string.extreme)
    }
    else {
        UiText.StringResourceValue(R.string.low)
    }
}
fun getWeatherIcon(code: Int?): Int{
    return when (code) {
        1000 -> com.optmizedcode.assets.R.drawable.ic_1000
        1003 -> com.optmizedcode.assets.R.drawable.ic_1003
        1006 -> com.optmizedcode.assets.R.drawable.ic_1006
        1009 -> com.optmizedcode.assets.R.drawable.ic_1009
        1030 -> com.optmizedcode.assets.R.drawable.ic_1030
        1063 -> com.optmizedcode.assets.R.drawable.ic_1063
        1066 -> com.optmizedcode.assets.R.drawable.ic_1066
        1069 -> com.optmizedcode.assets.R.drawable.ic_1069
        1072 -> com.optmizedcode.assets.R.drawable.ic_1072
        1087 -> com.optmizedcode.assets.R.drawable.ic_1087
        1114 -> com.optmizedcode.assets.R.drawable.ic_1114
        1117 -> com.optmizedcode.assets.R.drawable.ic_1117
        1135 -> com.optmizedcode.assets.R.drawable.ic_1135
        1147 -> com.optmizedcode.assets.R.drawable.ic_1147
        1150 -> com.optmizedcode.assets.R.drawable.ic_1150
        1153 -> com.optmizedcode.assets.R.drawable.ic_1153
        1168 -> com.optmizedcode.assets.R.drawable.ic_1168
        1171 -> com.optmizedcode.assets.R.drawable.ic_1171
        1188 -> com.optmizedcode.assets.R.drawable.ic_1180
        1183 -> com.optmizedcode.assets.R.drawable.ic_1183
        1186 -> com.optmizedcode.assets.R.drawable.ic_1186
        1189 -> com.optmizedcode.assets.R.drawable.ic_1189
        1192 -> com.optmizedcode.assets.R.drawable.ic_1192
        1195 -> com.optmizedcode.assets.R.drawable.ic_1195
        1198 -> com.optmizedcode.assets.R.drawable.ic_1198
        1201 -> com.optmizedcode.assets.R.drawable.ic_1201
        1204 -> com.optmizedcode.assets.R.drawable.ic_1204
        1207 -> com.optmizedcode.assets.R.drawable.ic_1207
        1210 -> com.optmizedcode.assets.R.drawable.ic_1210
        1213 -> com.optmizedcode.assets.R.drawable.ic_1213
        1216 -> com.optmizedcode.assets.R.drawable.ic_1216
        1219 -> com.optmizedcode.assets.R.drawable.ic_1219
        1222 -> com.optmizedcode.assets.R.drawable.ic_1222
        1225 -> com.optmizedcode.assets.R.drawable.ic_1225
        1237 -> com.optmizedcode.assets.R.drawable.ic_1237
        1240 -> com.optmizedcode.assets.R.drawable.ic_1240
        1243 -> com.optmizedcode.assets.R.drawable.ic_1243
        1246 -> com.optmizedcode.assets.R.drawable.ic_1246
        1249 -> com.optmizedcode.assets.R.drawable.ic_1249
        1252 -> com.optmizedcode.assets.R.drawable.ic_1252
        1255 -> com.optmizedcode.assets.R.drawable.ic_1255
        1258 -> com.optmizedcode.assets.R.drawable.ic_1258
        1261 -> com.optmizedcode.assets.R.drawable.ic_1261
        1264 -> com.optmizedcode.assets.R.drawable.ic_1264
        1273 -> com.optmizedcode.assets.R.drawable.ic_1273
        1276 -> com.optmizedcode.assets.R.drawable.ic_1276
        1279 -> com.optmizedcode.assets.R.drawable.ic_1279
        1282 -> com.optmizedcode.assets.R.drawable.ic_1282
        else -> {
            com.optmizedcode.assets.R.drawable.ic_1000
        }
    }
}