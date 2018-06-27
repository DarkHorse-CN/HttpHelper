package com.darkhorse.httphelper.`interface`

import okhttp3.Response

/**
 * Description:
 * Created by DarkHorse on 2018/6/13.
 */
interface IDoubleToken {
    fun getShortTokenKey(): String

    fun getLongTokenKey(): String

    fun getShortToken(): String

    fun getLongToken(): String

    fun isShortTokenExpire(result: String): Boolean

    fun refreshShortToken()
}