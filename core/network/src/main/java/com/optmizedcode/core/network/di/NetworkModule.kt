package com.optmizedcode.core.network.di

import android.util.Log
import com.optmizedcode.core.network.ApiService
import com.optmizedcode.core.network.BuildConfig
import com.optmizedcode.core.network.dataproviders.WeatherDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import java.net.URLDecoder
import java.security.NoSuchAlgorithmException

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author ehtisham
 * @package com.optmizedcode.core.network.di
 * @date 10-Sep-2023
 * @copyright 2023 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val CONNECT_TIMEOUT_MULTIPLIER = 1
    private const val DEFAULT_CONNECT_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    private const val DEFAULT_WRITE_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    private const val DEFAULT_READ_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    private const val NO_OF_LOG_CHAR = 2000
    private const val API_USER_NAME = "optimized_code"
    private const val API_PASSWORD = "WwF4HJWh9pr0ry0v"
    private const val API_AUTH_TYPE = "sha256"
    private val sDispatcher: Dispatcher? = null
    private var authKey: String = ""
    private const val TAG = "Weather_Network"

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.APP_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(getOkHttpClientBuilder().build())
            .build().create(ApiService::class.java)
    }

    private fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        /*OkHttp client builder*/
        val oktHttpClientBuilder = OkHttpClient.Builder().followRedirects(true)
            .connectTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_CONNECT_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )
            .writeTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_WRITE_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_READ_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )

        oktHttpClientBuilder.dispatcher(getDispatcher())
        oktHttpClientBuilder.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
                //.addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("authKey", "")
                .addHeader("apiAuthType", API_AUTH_TYPE)
//                .addHeader("token", AuthKeyHelper.getInstance().token.toString())
                //.addHeader("Authorization", GlobalAppSharedPref.customerBearerToken ?: "")
            //.addHeader("Authorization", ApplicationConstants.ADMIN_TOKEN)

            chain.proceed(builder.build())
        }

        oktHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor())
        oktHttpClientBuilder.addInterceptor { chain ->
            var request = chain.request()

            printPostmanFormattedLog(request)

            var response = chain.proceed(request)
            Log.d(TAG, "intercept: " + response.code)
            val token = response.header("token")
            if (response.code == 401 && token != null && token.isNotEmpty()) {
                val usernamePasswordMd5 =
                    getMd5String("$API_USER_NAME:$API_PASSWORD")
                authKey =
                    getMd5String("$usernamePasswordMd5:$token")
                Log.d(TAG, "token: $token")
                response.close()
                request =
                    request.newBuilder().header("authKey", authKey)
                        .build()
                response = chain.proceed(request)
            }
            response
        }
        return oktHttpClientBuilder
    }

    private fun getMd5String(stringToConvert: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest
                .getInstance(API_AUTH_TYPE)
//                        .getInstance("MD5")
            digest.update(stringToConvert.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun printPostmanFormattedLog(request: Request) {
        try {
            val allParams: String = if (request.method == "GET" || request.method == "DELETE") {
                request.url.toString().substring(
                    request.url.toString().indexOf("?") + 1,
                    request.url.toString().length
                )
            } else {
                val buffer = Buffer()
                request.body!!.writeTo(buffer)
                buffer.readString(Charset.forName("UTF-8"))
            }
            val params =
                allParams.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val paramsString = StringBuilder("\n")
            for (param in params) {
                paramsString.append(decode(param.replace("=", ":")))
                paramsString.append("\n")
            }
            Log.d(TAG, paramsString.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun decode(url: String): String {
        return try {
            var prevURL = ""
            var decodeURL = url
            while (prevURL != decodeURL) {
                prevURL = decodeURL
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8")
            }
            decodeURL
        } catch (e: UnsupportedEncodingException) {
            "Issue while decoding" + e.message
        }
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor =
            HttpLoggingInterceptor { message ->
                if (message.length > NO_OF_LOG_CHAR) {
                    for (noOfLogs in 0..message.length / NO_OF_LOG_CHAR) {
                        if (noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR < message.length) {
                            Log.d(
                                TAG,
                                message.substring(
                                    noOfLogs * NO_OF_LOG_CHAR,
                                    noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR
                                )
                            )
                        } else {
                            Log.d(
                                TAG,
                                message.substring(noOfLogs * NO_OF_LOG_CHAR, message.length)
                            )
                        }
                    }
                } else {
                    Log.d(TAG, message)
                }
            }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //loggingInterceptor.redactHeader("Authorization")
        loggingInterceptor.redactHeader("Cookie")
        return loggingInterceptor
    }

    private fun getDispatcher(): Dispatcher {
        return sDispatcher ?: Dispatcher()
    }

    @Provides
    fun provideWeatherReportDataProvider(apiService: ApiService): WeatherDataProviders{
        return WeatherDataProviders(apiService)
    }
}