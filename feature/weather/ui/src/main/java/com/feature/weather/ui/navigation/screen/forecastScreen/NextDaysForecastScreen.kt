package com.feature.weather.ui.navigation.screen.forecastScreen

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feature.weather.domain.model.Day
import com.feature.weather.domain.model.Forecastday
import com.feature.weather.domain.model.Hour
import com.feature.weather.ui.R
import com.feature.weather.ui.navigation.composables.AdditionalInfoRow
import com.feature.weather.ui.navigation.composables.ForecastListRow
import com.feature.weather.ui.navigation.composables.TemperatureGradientBg
import com.feature.weather.ui.navigation.composables.WhiteRoundedCornerBackground
import com.feature.weather.ui.navigation.composables.gradientBg
import com.feature.weather.ui.navigation.composables.gradientBg1
import com.feature.weather.ui.navigation.faketheme.theme.SmartWeatherForecastAppTheme_Fake
import com.feature.weather.ui.navigation.helpers.getWeatherIcon
import com.feature.weather.ui.navigation.screen.Portrait5Inch
import com.optmizedcode.core.common.helpers.DateTimeUtils.convertEpochDateIntoFormattedString
import com.optmizedcode.core.common.helpers.DateTimeUtils.isDay

/*
**************************************************************
 * www.optimizedcode.io 
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package com.feature.weather.ui.navigation.screen
 * @date 23-Nov-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Portrait5Inch
@Composable
fun WeatherReportScreen_onlyForPreview() {
    SmartWeatherForecastAppTheme_Fake {
        NextDaysForecastScreen_preview()
    }
}

@Composable
fun NextDaysForecastScreen_preview() {
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(gradientBg())
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(id = R.color.wa_lightBlueVariant))
                .fillMaxSize()
        ) {
            val yOffset = 0.26 * this.maxHeight.value

            // White background with rounded corners
            WhiteRoundedCornerBackground(modifier = Modifier.align(Alignment.BottomCenter))
            // Day wise
            ForecastListRow(
                modifier = Modifier
                    .statusBarsPadding()
                    .then(Modifier.padding(top = 15.dp)),
                backgroundColor = colorResource(id = R.color.white),
                fontColor = colorResource(id = R.color.wa_secondary),
                hours = arrayListOf(Hour(), Hour())
            )
            // Days list
            DaysListRow(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(.3f),
                forecastDays = arrayListOf(Forecastday())
            )
            // Inner elevated box
            CurrentWeatherCard(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                yOffset = yOffset
            )
        }
    }
}

@Composable
fun NextDaysForecastScreen(viewModel: ForecastScreenViewModel) {
    val result = viewModel.forecastData
    BoxWithConstraints(
        modifier = Modifier
            .background(colorResource(id = R.color.wa_whiteBlack))
            .fillMaxSize()
    ) {

        var selectedDay by remember { mutableIntStateOf(0) }
        val yOffset = 0.22 * this.maxHeight.value

        if (result.isLoading) {
            CircularProgressIndicator()
        }

        if (result.error.isNotEmpty()) {
            Text(result.error)
        }

        result.success?.let {
            WhiteRoundedCornerBackground(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        gradientBg1(),
                        shape = RoundedCornerShape(topStartPercent = 7, topEndPercent = 7)
                    )
            )
            // Hour wise
            ForecastListRow(
                modifier = Modifier.padding(top = 20.dp),
                hours = it[selectedDay].hour,
                backgroundColor = colorResource(id = R.color.wa_secondary),
                fontColor = colorResource(id = R.color.white)
            )
            // Days list
            DaysListRow(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(.33f)
                    .padding(bottom = 10.dp),
                forecastDays = it,
                onDayClick = { index ->
                    selectedDay = index
                }
            )
            // Inner elevated box
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.4f)
                    .offset(y = yOffset.dp)
                    .align(Alignment.TopCenter),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(15),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradientBg1()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.6f)
                    ) {

                        // TODO calculate isDay, subtitle, chances of rain
                        InnerCardLeftColumn(
                            modifier = Modifier.weight(1f),
                            title = it[selectedDay].day?.condition?.text ?: "",
                            subtitle = "Midnight",
                            icon = getWeatherIcon(
                                it[selectedDay].day?.condition?.code,
                                isDay()
                            )
                        )
                        InnerCardRightColumn(
                            modifier = Modifier.weight(1f),
                            temperature = it[selectedDay].day?.avgtemp_c?.toInt()
                                .toString(),
                            feelsLike = "${stringResource(id = R.string.feels_like)} ${
                                it[selectedDay].day?.maxtemp_c?.toInt().toString()
                            }",
                            chancesOfRain = stringResource(
                                R.string.chances_of_rain,
                                it[selectedDay].day?.daily_chance_of_rain?.toInt()
                                    .toString()
                            ),
                            uv = stringResource(
                                R.string.uv_index_s,
                                it[selectedDay].day?.uv?.toInt().toString()
                            )
                        )

                    }
                    AdditionalInfoRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.4f),
                        isAnimatable = false,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        backgroundColor = colorResource(id = R.color.white),
                        humidity = it[selectedDay].day?.avghumidity?.toInt(),
                        windSpeed = it[selectedDay].day?.maxwind_kph,
                        visibility = it[selectedDay].day?.avgvis_km?.toInt(),
                        icon1 = com.optmizedcode.assets.R.drawable.ic_humidity_5,
                        icon2 = com.optmizedcode.assets.R.drawable.ic_breezy_1
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherCard(modifier: Modifier = Modifier, yOffset: Double = 0.26) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.6f)
            .offset(y = yOffset.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBg1()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.6f)
            ) {
                InnerCardLeftColumn(
                    modifier = Modifier.weight(1f)
                )
                InnerCardRightColumn(
                    modifier = Modifier.weight(1f)
                )
            }
            AdditionalInfoRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.4f),
                textColor = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = colorResource(id = R.color.wa_lightGrey),
                icon1 = com.optmizedcode.assets.R.drawable.ic_humidity_5,
                icon2 = com.optmizedcode.assets.R.drawable.ic_breezy_1
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DaysListRow(
    modifier: Modifier = Modifier,
    forecastDays: ArrayList<Forecastday> = arrayListOf(),
    onDayClick: (index: Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        state = rememberLazyListState()
    ) {
        stickyHeader(key = "header") {
            Text(text = stringResource(id = R.string.forecasted_days))
        }

        itemsIndexed(items = forecastDays) { index, item ->
            DaysRowItem(
                date = item.date_epoch,
                day = item.day,
                onDayClick = { onDayClick(index) }
            )
        }
    }
}

@Composable
fun InnerCardLeftColumn(
    modifier: Modifier,
    @DrawableRes icon: Int = com.optmizedcode.assets.R.drawable.cloud_3d_200,
    title: String = "Heavy rain",
    subtitle: String = "Midnight"
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 18.sp
            ),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun InnerCardRightColumn(
    modifier: Modifier,
    temperature: String = "26",
    feelsLike: String = "Feels like 32°",
    chancesOfRain: String = "Chances of rain 50%",
    uv: String = "UV index 8"
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$temperature°",
            style = MaterialTheme.typography.displayLarge
                .copy(fontSize = 70.sp, brush = TemperatureGradientBg()),
            fontSize = 70.sp
        )

        Text(
            text = "$feelsLike°",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = chancesOfRain,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Text(
            text = uv,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DaysRowItem(
    modifier: Modifier = Modifier,
    date: Long? = 1701129600L,
    day: Day? = Day(),
    backgroundColor: Color = colorResource(id = R.color.wa_whiteBlack),
    onDayClick: () -> Unit = {}
) {

    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    val density = LocalDensity.current
    AnimatedVisibility(
        visibleState = state,
        enter = slideInVertically(
            initialOffsetY = { with(density) { 20.dp.roundToPx() } },
            animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        ) + fadeIn(initialAlpha = 0.1f)
        //+ expandVertically(expandFrom = Alignment.Top)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onDayClick() },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = convertEpochDateIntoFormattedString(date),
                    color = colorResource(id = R.color.wa_secondary),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = "${day?.maxtemp_c?.toInt() ?: "17"}°/",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.wa_gray),
                        modifier = Modifier.wrapContentHeight()
                    )
                    Text(
                        text = "${day?.mintemp_c?.toInt() ?: "22"}°",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = R.color.wa_gray),
                        modifier = Modifier.wrapContentHeight()
                    )
                }

                Column(
                    modifier = Modifier
                        .width(55.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(
                            id = getWeatherIcon(day?.condition?.code, isDay())
                        ),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        text = day?.condition?.text ?: "cloudy",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorResource(id = R.color.purple_500),
                        modifier = Modifier.padding(bottom = 5.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}



