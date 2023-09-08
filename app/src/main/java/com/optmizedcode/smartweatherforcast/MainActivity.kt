package com.optmizedcode.smartweatherforcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.optmizedcode.smartweatherforcast.helpers.AppConstants
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.get
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.set
import com.optmizedcode.smartweatherforcast.helpers.KeysHelper
import com.optmizedcode.smartweatherforcast.ui.theme.SmartWeatherForcastAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartWeatherForcastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppPrefs.prefs()[KeysHelper.LANGUAGE] = KeysHelper.LANGUAGE_AR
                    AppPrefs.prefs()["int_val"] = 1
                    AppPrefs.prefs()["float_val"] = 20.0f
                    AppPrefs.prefs()["boolean_val"] = false
                    AppPrefs.prefs()["long_val"] = 10000000L
                    val language = AppPrefs.prefs().get<String>(KeysHelper.LANGUAGE)
                    Greeting("${getString(R.string.app_name)} - with language: $language")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, locale = AppConstants.currentLocal)
@Composable
fun GreetingPreview() {
    SmartWeatherForcastAppTheme {
        Greeting(stringResource(id = R.string.app_name), modifier = Modifier.padding(20.dp))
    }
}