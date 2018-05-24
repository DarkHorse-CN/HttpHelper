package com.darkhorse.httphelper

import android.content.Context
import com.darkhorse.httphelper.converter.BaseConvert
import com.darkhorse.httphelper.converter.BaseConverterFactory

import com.darkhorse.httphelper.client.HttpClient
import com.darkhorse.httphelper.client.RetrofitClient
import com.darkhorse.httphelper.interceptor.DoubleTokenInterceptor
import com.darkhorse.httphelper.interceptor.MulUrlInterceptor
import com.darkhorse.httphelper.interceptor.TokenInterceptor

import java.util.HashMap

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by DarkHorse on 2018/2/4.
 */

object HttpHelper {

    private var mRetrofit: Retrofit? = null

    /**
     * 添加BaseUrl
     */
    fun addBaseUrl(url: String): HttpHelper {
        RetrofitClient.retrofitBuilder.baseUrl(url)
        return this
    }

    /**
     * 通过添加@Header，实现多种BaseUrl的支持
     */
    fun supportMulBaseUrl(urlMap: HashMap<String, String>): HttpHelper {
        HttpClient.addInterceptor(MulUrlInterceptor(urlMap))
        return this
    }

    /**
     * 添加单Token请求机制
     */
    fun supportSingleToken(tokenName: String): HttpHelper {
        HttpClient.addInterceptor(TokenInterceptor(tokenName))
        return this
    }

    /**
     * 添加双Token请求机制
     */
    fun supportDoubleToken(shortTokenKey: String, longTokenKey: String, isTokenExpire: (data: String) -> Boolean, refreshToken: () -> String): HttpHelper {
        HttpClient.addInterceptor(DoubleTokenInterceptor(shortTokenKey, longTokenKey, isTokenExpire, refreshToken))
        return this
    }

    /**
     * 添加Cookie支持
     */
    fun addCookieAutoManager(context: Context): HttpHelper {
        HttpClient.addCookieJar(context)
        return this
    }

    fun setConvert(converter: BaseConvert): HttpHelper {
        RetrofitClient.retrofitBuilder.addConverterFactory(BaseConverterFactory(converter))
        return this
    }

    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor): HttpHelper {
        HttpClient.addInterceptor(interceptor)
        return this
    }

    /**
     * 设置超时时长
     */
    fun setTimeout(connectTimeout: Long, readTimeout: Long, timeUnit: TimeUnit) {
        HttpClient.setTimeout(connectTimeout, readTimeout, timeUnit)
    }


    fun init(): Boolean {
        RetrofitClient.retrofitBuilder.client(HttpClient.mBuilder.build())
        mRetrofit = RetrofitClient.retrofit
        return mRetrofit != null
    }

    fun <T> getService(cls: Class<T>): T {
        return mRetrofit!!.create(cls)
    }

}
