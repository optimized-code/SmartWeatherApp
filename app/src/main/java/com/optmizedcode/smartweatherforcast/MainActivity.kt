package com.optmizedcode.smartweatherforcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.feature.weather.ui.navigation.screen.gradientBg
import com.optmizedcode.smartweatherforcast.navigation.AppNavGraph
import com.optmizedcode.smartweatherforcast.navigation.NavigationProvider
import com.optmizedcode.smartweatherforcast.ui.theme.SmartWeatherForcastAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SmartWeatherForcastAppTheme {
                val navController = rememberNavController()
                App(navHostController = navController, navigationProvider)
            }
        }
    }
}

@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBg())
    ) {
        AppNavGraph(
            navController = navHostController,
            navigationProvider = navigationProvider,
        )
    }
}