package com.darkhorse.httphelper

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * Description:
 * Created by DarkHorse on 2018/5/22.
 */
object NetworkUtils {

    /**
     * 判断网络是否可用
     */
    fun isNetworkAvailable(context: Context) = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.isAvailable

    /**
     * 判断wifi网络是否可用
     */
    fun isWifiAvailable(context: Context): Boolean {
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
    fun isMobileAvailable(context: Context): Boolean {
        val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return networkInfo.isAvailable
        }
        return false
    }

}
