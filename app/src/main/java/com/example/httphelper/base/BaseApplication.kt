package com.example.httphelper.base

import android.app.Application
import com.darkhorse.httphelper.HttpHelper
import com.darkhorse.httphelper.HttpHelper.supportDoubleToken
import com.darkhorse.httphelper.`interface`.IDoubleToken
import com.example.httphelper.retrofit.API
import com.example.httphelper.retrofit.MyConverter
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by DarkHorse on 2018/2/4.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initHttpUtils()    //初始化网络请求工具类
    }

    private fun initHttpUtils() {
        val map = HashMap<String, String>()

        HttpHelper
                .addBaseUrl(API.SHOP)
                .supportMulBaseUrl(map)     //实现多BaseUrl的支持
                .supportDoubleToken(API.SHORT_TOKEN, API.LONG_TOKEN, object : IDoubleToken {
                    override fun getShortToken(): String {
                        return ""
                    }

                    override fun getLongToken(): String {
                        return ""
                    }

                    override fun isShortTokenExpire(result: String): Boolean {
                        return false
                    }

                    override fun refreshShortToken() {

                    }
                })
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .setConvert(MyConverter())
                .init()
        initBaseUrl(map)
    }

    private fun isTokenExpire(data: String): Boolean {

        return true
    }

    private fun refreshToken(): String {

        return "newToken"
    }

    private fun initBaseUrl(map: MutableMap<String, String>) {
        map["movie"] = API.MOVIE
        map["music"] = API.MUSIC
        map["hhh"] = API.HHH
    }
}
