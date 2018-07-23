package com.example.httphelper.retrofit

import com.darkhorse.httphelper.HttpHelper

/**
 * Created by DarkHorse on 2018/2/4.
 */

class API {
    companion object {
        const val MUSIC = "https://api.douban.com/"
        const val MOVIE = "https://api.douban.com/"
        const val SHOP = "http://kj.goodbooy.cn/api/"
        const val HHH = "http://hhh.ljzchris.cn/api/"
        const val SUCCESS_CODE = 1

        val movieService = HttpHelper.getService(MovieService::class.java)
        val musicService = HttpHelper.getService(MusicService::class.java)
        val shopService = HttpHelper.getService(HHHService::class.java)
    }
}
