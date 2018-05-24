package com.example.httphelper.retrofit

import com.example.httphelper.bean.MusicResult

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by DarkHorse on 2018/2/4.
 */

interface MusicService {

    @Headers("base_url:music")
    @GET("v2/music/search")
    fun getMusic(@Query("q") q: String): Observable<MusicResult>

}
