package com.optmizedcode.smartweatherforcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.optmizedcode.smartweatherforcast.helpers.AppConstants
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.set
import com.optmizedcode.smartweatherforcast.helpers.KeysHelper
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
        setContent {
            SmartWeatherForcastAppTheme {
//                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                App(navHostController = navController, navigationProvider)
            }
        }
    }
}

@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavGraph(navController = navHostController, navigationProvider = navigationProvider)
    }
}

@Preview(showBackground = true, locale = AppConstants.currentLocale)
@Composable
fun GreetingPreview() {
    SmartWeatherForcastAppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}