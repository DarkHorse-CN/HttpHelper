package com.darkhorse.httphelper.client

import com.darkhorse.httphelper.converter.BaseConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by DarkHorse on 2018/2/4.
 */

object RetrofitClient {

    val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    val retrofit: Retrofit
        get() = retrofitBuilder.build()

}
