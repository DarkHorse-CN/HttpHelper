package com.darkhorse.httphelper.interceptor

import com.darkhorse.preferencesmanager.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description:
 * Created by DarkHorse on 2018/5/16.
 */
class TokenInterceptor(var tokenName: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(tokenName, PreferencesHelper[tokenName, "token"] as String)
        return chain.proceed(builder.build())
    }
}
