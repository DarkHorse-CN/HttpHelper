package com.darkhorse.httphelper.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description:
 * Created by DarkHorse on 2018/5/16.
 */
class TokenInterceptor(private var tokenKey: String, private var getToken: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(tokenKey, getToken())
        return chain.proceed(builder.build())
    }
}
