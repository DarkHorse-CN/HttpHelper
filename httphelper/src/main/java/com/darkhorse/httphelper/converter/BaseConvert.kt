package com.darkhorse.httphelper.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.RequestBody
import okhttp3.ResponseBody

/**
 * Description:
 * Created by DarkHorse on 2018/5/14.
 */
interface BaseConvert {
    fun beanToRequest(gson: Gson, adapter: TypeAdapter<out Any>, bean: Any): RequestBody?


    fun responseToBean(gson: Gson, adapter: TypeAdapter<out Any>, responseBody: ResponseBody): Any?
}
