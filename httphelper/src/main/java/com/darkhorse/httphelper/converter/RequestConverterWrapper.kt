package com.darkhorse.httphelper.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.RequestBody
import retrofit2.Converter

/**
 * Description:
 * Created by DarkHorse on 2018/5/14.
 */
class RequestConverterWrapper(gson: Gson, adapter: TypeAdapter<out Any>, converter: BaseConvert) : Converter<Any, RequestBody> {

    private val mGson: Gson = gson
    private val mAdapter: TypeAdapter<out Any> = adapter
    private val mConverter = converter

    override fun convert(value: Any): RequestBody? {
        return mConverter.beanToRequest(mGson, mAdapter, value)
    }

}
