package com.darkhorse.httphelper.interceptor

import com.darkhorse.httphelper.minterface.ISingleToken
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description:
 * Created by DarkHorse on 2018/5/16.
 */
class TokenInterceptor(private val iSingleToken: ISingleToken) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(iSingleToken.getTokenKey(), iSingleToken.getToken())
        return chain.proceed(builder.build())
    }
}
