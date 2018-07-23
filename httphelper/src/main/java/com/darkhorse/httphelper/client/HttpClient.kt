package com.darkhorse.httphelper.client

import android.content.Context

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by DarkHorse on 2018/2/4.
 */

object HttpClient {

    internal val mBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    fun addInterceptor(interceptor: Interceptor): HttpClient {
        mBuilder.addInterceptor(interceptor)
        return this
    }

    fun setTimeout(connectTimeout: Long, readTimeout: Long, timeUnit: TimeUnit) {
        mBuilder.connectTimeout(connectTimeout, timeUnit)
        mBuilder.readTimeout(readTimeout, timeUnit)
    }

    fun addCookieJar(context: Context): HttpClient {
        mBuilder.cookieJar(object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                val sp = context.getSharedPreferences("HttpPreferences", 0)
                val editor = sp.edit()
                val json = Gson().toJson(cookies)
                editor.putString(url.toString(), json)
                editor.apply()
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                val sp = context.getSharedPreferences("HttpPreferences", 0)
                val json = sp.getString(url.toString(), "")
                return Gson().fromJson(json, object : TypeToken<List<Cookie>>() {
                }.type)
            }
        })
        return this
    }
}
