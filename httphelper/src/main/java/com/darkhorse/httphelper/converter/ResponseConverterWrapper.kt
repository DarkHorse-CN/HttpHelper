package com.darkhorse.httphelper.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Description:
 * Created by DarkHorse on 2018/5/14.
 */
class ResponseConverterWrapper(gson: Gson, adapter: TypeAdapter<out Any>, responseConverter: BaseConvert) : Converter<ResponseBody, Any> {

    private val mGson: Gson = gson
    private val mAdapter = adapter
    private val mConverter = responseConverter

    override fun convert(value: ResponseBody): Any? {
        return mConverter.responseToBean(mGson, mAdapter, value)
    }

}
