package com.darkhorse.httphelper.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


/**
 * Description:
 * Created by DarkHorse on 2018/5/14.
 */

class BaseConverterFactory(converter: BaseConvert) : Converter.Factory() {

    val mGson = Gson()
    val mConverter = converter

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        val adapter = mGson.getAdapter(TypeToken.get(type))
        return RequestConverterWrapper(mGson, adapter, mConverter)
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val adapter = mGson.getAdapter(TypeToken.get(type))
        return ResponseConverterWrapper(mGson, adapter, mConverter)
    }

}
