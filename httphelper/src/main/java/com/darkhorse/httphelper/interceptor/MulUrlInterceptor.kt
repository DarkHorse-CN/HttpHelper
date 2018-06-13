package com.example.httphelper.interceptor

import java.io.IOException
import java.util.HashMap

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by DarkHorse on 2018/2/4.
 */

class MulUrlInterceptor(private val mHashMap: HashMap<String, String>) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        val builder = request.newBuilder()
        val headers = request.headers("base_url")
        if (headers != null && headers.size > 0) {
            builder.removeHeader("base_url")
            val headerValue = headers[0]
            val newUrl = HttpUrl.parse(mHashMap[headerValue])
            val oldUrl = request.url()
            val fullUrl = oldUrl
                    .newBuilder()
                    .scheme(newUrl!!.scheme())
                    .host(newUrl.host())
                    .port(newUrl.port())
                    .build()
            return chain.proceed(builder.url(fullUrl).build())
        }
        return chain.proceed(builder.build())
    }
}
