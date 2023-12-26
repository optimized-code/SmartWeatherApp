package com.optmizedcode.smartweatherforcast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.feature.weather.ui.navigation.composables.gradientBg
import com.feature.weather.ui.navigation.screen.homeScreen.AppNavigationBar
import com.optmizedcode.smartweatherforcast.navigation.AppNavGraph
import com.optmizedcode.smartweatherforcast.navigation.NavigationProvider
import com.optmizedcode.smartweatherforcast.navigation.ScreenRoutes
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
                App(navHostController = rememberNavController(), navigationProvider)
            }
        }
    }
}

@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider) {
    val screens = listOf(
        ScreenRoutes.WeatherReportScreen,
        ScreenRoutes.SearchCityScreen,
        ScreenRoutes.FavoritesScreen,
        ScreenRoutes.SettingsScreen
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarVisibility = rememberSaveable { mutableStateOf(true) }
    bottomBarVisibility.value = screens.any { it.route == currentDestination?.route }
    val modifier = if (bottomBarVisibility.value) {
        Modifier.navigationBarsPadding()
    } else {
        Modifier
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarVisibility.value,
//                    enter = fadeIn(),
//                    exit = fadeOut()
                    enter = slideInVertically(
                        animationSpec = tween(300)
                    ) {
                        it
                    },
                    exit = slideOutVertically(
                        animationSpec = tween(300)
                    ) {
                        it
                    }
                ) {
                    AppNavigationBar()
                }
            },
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBg())
                .then(modifier)
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AppNavGraph(
                    navController = navHostController,
                    navigationProvider = navigationProvider,
                )
            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}