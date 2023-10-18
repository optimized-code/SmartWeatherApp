package com.feature.weather.ui.navigation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.weather.ui.R

/*
**************************************************************
 * www.optmizedcode.com 
 * Kotlin
 *
 * @author ehtisham
 * @package com.feature.weather.ui.navigation.screen
 * @date 08-Oct-2023
 * @copyright 2023 Optimized code (https://www.optmizedcode.com)
 * @license Open source
 ***************************************************************
 */

@Composable
fun WeatherReportScreen(viewModel: TodayWeatherReportViewModel) {

    val result = viewModel.weatherReportData.value

    Scaffold(
        topBar = {
            Text("Weather Report")
        }
    ) {
        Log.d("TAG", "Weather screen $it")
        if (result.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        if (result.error.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(result.error)
            }
        }

        result.success?.let {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(it.current.condition?.text ?: "There is something")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherHomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBg()),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationText()
        }
    }
}


@Composable
fun gradientBg(): Brush {
    return Brush.radialGradient(
        colors = listOf(
            colorResource(id = R.color.wa_primary),
            colorResource(id = R.color.wa_secondary),
        ),
        radius = 1800.0f,
        center = Offset.Zero
    )
}

@Composable
fun LocationText() {
    Text(
        text = "Makkah",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 20.dp),
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.SemiBold
        )
    )
}