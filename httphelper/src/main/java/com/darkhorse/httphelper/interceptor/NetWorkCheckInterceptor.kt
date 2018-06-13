package com.example.httphelper.interceptor

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.darkhorse.httphelper.NetworkUtils.isNetworkAvailable
import com.darkhorse.httphelper.R
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.ref.WeakReference

/**
 * Description:
 * Created by DarkHorse on 2018/6/7.
 */
class NetWorkCheckInterceptor(context: Context, private val errorMethod: () -> Unit) : Interceptor {

    private var mWeakReference: WeakReference<Context> = WeakReference(context)

    override fun intercept(chain: Interceptor.Chain): Response? {
        if (!isNetworkAvailable(mWeakReference.get()!!)) {
            chain.call().cancel()
            errorMethod()
        }
        return chain.proceed(chain.request())
    }

    /**
     * 判断网络是否可用
     */
    private fun isNetworkAvailable(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        if (info != null) {
            return info.isAvailable
        }
        return false
    }

    /**
     * 判断wifi网络是否可用
     */
    private fun isWifiAvailable(context: Context): Boolean {
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
            return networkInfo.isAvailable
        }
        return false
    }

    /**
     * 判断mobile网络是否可用
     */
    private fun isMobileAvailable(context: Context): Boolean {
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return networkInfo.isAvailable
        }
        return false
    }
}
