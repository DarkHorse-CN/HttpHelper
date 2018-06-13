package com.darkhorse.httphelper

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import com.darkhorse.httphelper.`interface`.IDoubleToken
import com.darkhorse.httphelper.converter.BaseConvert
import com.darkhorse.httphelper.converter.BaseConverterFactory

import com.darkhorse.httphelper.client.HttpClient
import com.darkhorse.httphelper.client.RetrofitClient
import com.darkhorse.httphelper.interceptor.DoubleTokenInterceptor
import com.example.httphelper.interceptor.MulUrlInterceptor
import com.darkhorse.httphelper.interceptor.TokenInterceptor
import com.example.httphelper.interceptor.NetWorkCheckInterceptor
import com.example.httphelper.interceptor.RetryInterceptor

import java.util.HashMap

import okhttp3.Interceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by DarkHorse on 2018/2/4.
 */

object HttpHelper {

    private var mRetrofit: Retrofit? = null
    private var hasSetUrl = false

    private val mInterceptorList by lazy {
        ArrayList<Interceptor>()
    }
    private var mMulUrlInterceptor: MulUrlInterceptor? = null
    private var mNetWorkCheckInterceptor: NetWorkCheckInterceptor? = null
    private var mTokenInterceptor: TokenInterceptor? = null
    private var mDoubleTokenInterceptor: DoubleTokenInterceptor? = null
    private var mRetryInterceptor: RetryInterceptor? = null

    /**
     * 添加BaseUrl
     */
    fun addBaseUrl(url: String): HttpHelper {
        if (!hasSetUrl) {
            RetrofitClient.retrofitBuilder.baseUrl(url)
            hasSetUrl = true
        }
        return this
    }

    /**
     * 通过添加@Header，实现多种BaseUrl的支持
     */
    fun supportMulBaseUrl(urlMap: HashMap<String, String>): HttpHelper {
        if (!hasSetUrl) {
            mMulUrlInterceptor = MulUrlInterceptor(urlMap)
            hasSetUrl = true
        }
        return this
    }

    /**
     * 添加单Token请求机制
     */
    fun supportSingleToken(tokenName: String, getToken: () -> String): HttpHelper {
        if (mDoubleTokenInterceptor == null) {
            mTokenInterceptor = TokenInterceptor(tokenName, getToken)
        }
        return this

    }

    /**
     * 添加双Token请求机制
     */
    fun supportDoubleToken(shortTokenKey: String, longTokenKey: String, iDoubleToken: IDoubleToken): HttpHelper {
        if (mTokenInterceptor == null) {
            mDoubleTokenInterceptor = DoubleTokenInterceptor(shortTokenKey, longTokenKey, iDoubleToken)
        }
        return this
    }

    /**
     * 添加网络检查支持
     */
    fun supportNetworkCheck(context: Context, errorMethod: () -> Unit): HttpHelper {
        mNetWorkCheckInterceptor = NetWorkCheckInterceptor(context, errorMethod)
        return this
    }

    /**
     * 添加网络重试支持
     */
    fun supportRetry(hint: String) {
        mRetryInterceptor = RetryInterceptor(hint)
    }

    /**
     * 添加Cookie支持
     */
    fun addCookieAutoManager(context: Context): HttpHelper {
        HttpClient.addCookieJar(context)
        return this
    }

    /**
     *添加自定义转换器
     */
    fun setConvert(converter: BaseConvert): HttpHelper {
        RetrofitClient.retrofitBuilder.addConverterFactory(BaseConverterFactory(converter))
        return this
    }

    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor): HttpHelper {
        mInterceptorList.add(interceptor)
        return this
    }

    /**
     * 设置超时时长
     */
    fun setTimeout(connectTimeout: Long, readTimeout: Long, timeUnit: TimeUnit): HttpHelper {
        HttpClient.setTimeout(connectTimeout, readTimeout, timeUnit)
        return this
    }


    fun init() {
        if (mNetWorkCheckInterceptor != null) {
            HttpClient.addInterceptor(mNetWorkCheckInterceptor!!)
        }
        if (mMulUrlInterceptor != null) {
            HttpClient.addInterceptor(mMulUrlInterceptor!!)
        }
        if (mTokenInterceptor != null) {
            HttpClient.addInterceptor(mTokenInterceptor!!)
        }
        if (mDoubleTokenInterceptor != null) {
            HttpClient.addInterceptor(mDoubleTokenInterceptor!!)
        }

        for (i in mInterceptorList) {
            HttpClient.addInterceptor(i)
        }

        if (mRetryInterceptor != null) {
            HttpClient.addInterceptor(mRetryInterceptor!!)
        }
        RetrofitClient.retrofitBuilder.client(HttpClient.mBuilder.build())
        mRetrofit = RetrofitClient.retrofit
    }

    fun <T> getService(cls: Class<T>): T {
        return mRetrofit!!.create(cls)
    }

}
