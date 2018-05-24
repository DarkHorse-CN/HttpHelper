package com.example.httphelper.retrofit

import com.example.httphelper.bean.BaseResponse
import com.example.httphelper.bean.BillBean
import com.example.httphelper.bean.TestBean
import com.example.httphelper.bean.TokenBean

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Description:
 * Created by DarkHorse on 2018/5/14.
 */
interface HHHService {

    @GET("common/Protocol")
    fun getData(): Observable<BaseResponse<TestBean>>

    @Headers("base_url:hhh")
    @POST("pay_setting")
    fun getSetting(): Observable<BaseResponse<BillBean>>

//    @Headers(
//            "${API.LONG_TOKEN}:true",
//            "base_url:hhh")
//    @POST("apitoken_refresh")
//    fun refreshToken(): Observable<BaseResponse<TokenBean>>

    @Headers(
            "${API.LONG_TOKEN}:true",
            "base_url:hhh")
    @POST("apitoken_refresh")
    fun refreshToken(): Call<TokenBean>
}
