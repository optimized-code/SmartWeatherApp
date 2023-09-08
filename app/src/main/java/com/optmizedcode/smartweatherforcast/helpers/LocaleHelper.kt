package com.optmizedcode.smartweatherforcast.helpers
import android.content.Context
import android.content.res.Configuration
import com.optmizedcode.smartweatherforcast.helpers.AppPrefs.get
import java.util.*

class LocaleHelper {
    companion object {
        private var sLocale: Locale? = null

        fun updateConfig(context: Context) {
            //val myLocale: Locale = when (val lang = AppPrefs.get<String>(KeysHelper.LANGUAGE, PREF_TYPE.CONFIG)) {
            val myLocale: Locale = when (val lang = AppPrefs.prefs(PREFSTYPE.CONFIG).get<String>(KeysHelper.LANGUAGE)) {
                KeysHelper.LANGUAGE_EN -> Locale.ENGLISH
                KeysHelper.LANGUAGE_AR -> Locale.SIMPLIFIED_CHINESE
                else -> Locale(lang)
            }

            setLocale(myLocale)
            val config = Configuration()
            config.setLocale(sLocale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }

        private fun setLocale(locale: Locale) {
            sLocale = locale
            Locale.setDefault(sLocale!!)
        }
    }
}