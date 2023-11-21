package com.feature.weather.ui.navigation.composables

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.feature.weather.domain.model.Current
import com.feature.weather.domain.model.Hour
import com.feature.weather.domain.model.Location
import com.feature.weather.ui.navigation.helpers.getWeatherIcon
import com.optmizedcode.assets.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.composables
 * @date 10-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

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
                painter = painterResource(id = R.drawable.cloud_3d_200),
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
                text = stringResource(com.feature.weather.ui.R.string.today),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
                color = colorResource(id = com.feature.weather.ui.R.color.wa_blackWhite),
                modifier = Modifier.padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(com.feature.weather.ui.R.string.next_7_days),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,

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
        AdditionalInfoItem("${humidity}%", R.drawable.drizzle)
        AdditionalInfoItem("${windSpeed?.toInt()}km/h", R.drawable.breezy)
        AdditionalInfoItem("${visibility}km", R.drawable.visibility)
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
            color = colorResource(id = com.feature.weather.ui.R.color.wa_blackWhite),
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun HourlyRowItem(hour: Hour, currentHour: Int) {
    val time = hour.time?.split(" ")?.get(1)
    val incomingHour = time?.split(":")?.get(0)?.toInt()
    val textColor = if (incomingHour == currentHour) {
        colorResource(id = com.feature.weather.ui.R.color.white)
    } else {
        colorResource(id = com.feature.weather.ui.R.color.wa_secondary)
    }

    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (incomingHour == currentHour) {
                colorResource(id = com.feature.weather.ui.R.color.wa_secondary)
            } else {
                colorResource(id = com.feature.weather.ui.R.color.wa_whiteBlack)
            }

        ),
        shape = RoundedCornerShape(40.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .width(55.dp)
                .height(100.dp),
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
                painter = painterResource(
                    id = getWeatherIcon(
                        hour.condition?.code,
                        hour.isDay?.toInt() == 1
                    )
                ),
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
    icon: Int = R.drawable.ic_1000,
    heading: String = "Heading",
    value: String = "0",
    valueDescription: String = "Low",
    shortDescription: String = "Use sun protection 9AM - 4PM."
) {
    ElevatedCard(
        modifier = Modifier
            .size(150.dp)
            .background(Color.Transparent),
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
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(Color.Transparent)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp
                ),
                color = colorResource(id = com.feature.weather.ui.R.color.purple_500),
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
            .padding(horizontal = 25.dp),
            //.height(140.dp),
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
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_vector_wind),
                contentDescription = null, tint = Color.DarkGray,
                modifier = Modifier
                    .width(25.dp)
                    .padding(horizontal = 5.dp)
            )
            Text(
                text = stringResource(id = com.feature.weather.ui.R.string.wind),
                style = MaterialTheme.typography.titleSmall,
                color = Color.DarkGray
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
            ) {
                Text(
                    text = "$windKm",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 40.sp,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    color = colorResource(id = com.feature.weather.ui.R.color.purple_500)
                )
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = stringResource(com.feature.weather.ui.R.string.km_h),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = com.feature.weather.ui.R.string.wind_1),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }

            Divider()
//            Divider(color = Color.LightGray, thickness = 1.dp)

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    color = colorResource(id = com.feature.weather.ui.R.color.purple_500)
                )
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = stringResource(com.feature.weather.ui.R.string.km_h),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(com.feature.weather.ui.R.string.gust),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}