package com.darkhorse.httphelper.interceptor

import com.darkhorse.httphelper.HttpHelper
import com.darkhorse.preferencesmanager.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.locks.ReentrantLock

/**
 * Description:
 * Created by DarkHorse on 2018/5/17.
 */
class DoubleTokenInterceptor(private var shortTokenKey: String, private var longTokenKey: String, private var isTokenExpire: (data: String) -> Boolean, private var refreshToken: () -> String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers(longTokenKey)
        var response: Response;

        //判断是否刷新Token的请求
        if (headers.size <= 0) {
            val builder = request.newBuilder()

            builder.addHeader(shortTokenKey, PreferencesHelper[shortTokenKey, "shortToken"] as String)
            response = chain.proceed(builder.build())

            //验证shortToken是否失效
            if (isTokenExpire(response.body()!!.string())) {
                synchronized(this) {
                    PreferencesHelper.put(shortTokenKey, refreshToken())
                }
                val newRequest = chain.request()
                        .newBuilder()
                        .addHeader(shortTokenKey, PreferencesHelper[shortTokenKey, "shortToken"] as String)
                        .build()

                response = chain.proceed(newRequest)
            }
        } else {
            val builder = request.newBuilder()
            builder.removeHeader(longTokenKey)
            builder.addHeader(longTokenKey, PreferencesHelper[longTokenKey, "longToken"] as String)
            response = chain.proceed(builder.build())
        }
        return response
    }

}
