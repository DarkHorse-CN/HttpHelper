package com.example.httphelper.retrofit

import com.example.httphelper.bean.MovieResult

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by DarkHorse on 2018/2/4.
 */

interface MovieService {

    @Headers("base_url:movie")
    @GET("v2/movie/top250")
    fun getMovie(): Observable<MovieResult>
}
